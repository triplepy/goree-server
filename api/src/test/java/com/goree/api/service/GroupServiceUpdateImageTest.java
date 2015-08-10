package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.util.TestWithDBUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GroupServiceUpdateImageTest extends TestWithDBUnit {
	@Autowired
	private GroupService groupService;

	@Autowired
	private MemberMapper memberMapper;

    @Value("${file.upload.path}")
	private String fileUploadDir;

	private final String testImagePath = "src/test/resources/static/Image_upload_test.jpg";
	private File testImageFile;

	private final String multiFileName = "test.jpg";
	private MockMultipartFile multipartFile;

	@Override
	public String getDatasetFilePath() {
		return "src/test/resources/testdataset/group_test_setup.xml";
	}

	@Override
    @Before
	public void setUp() {
		super.setUp();

		testImageFile = new File(testImagePath);

		try (InputStream testImageStream = new FileInputStream(testImageFile)) {
			multipartFile = new MockMultipartFile(multiFileName, multiFileName, null, testImageStream);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("test image ("+ testImagePath +") is not found.", e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void updateImage_normal() throws IOException {
		// when
        updateAndAssert();
	}

    @Test
	public void updateImage_destDirNotExists() throws IOException {
		// remove destination directory
		File fileUploadDirObj = new File(fileUploadDir);
		if (fileUploadDirObj.exists() && fileUploadDirObj.isDirectory())
			FileUtils.forceDelete(fileUploadDirObj);

		// when
        updateAndAssert();
	}

    private void updateAndAssert() throws IOException {
        Group resultGroup = groupService.updateImage(multipartFile, 1L);
        assertThat(resultGroup.getImagePath(), is(not(nullValue())));

        String resultImagePath = FilenameUtils.concat(fileUploadDir, resultGroup.getImagePath());
        File resultFile = new File(resultImagePath);

        // then
        assertThat(resultGroup.getImagePath(), is(endsWith(multiFileName)));
        assertTrue(resultFile.exists());
        assertTrue(resultFile.isFile());
        assertTrue(FileUtils.contentEquals(resultFile, testImageFile));
    }
}
