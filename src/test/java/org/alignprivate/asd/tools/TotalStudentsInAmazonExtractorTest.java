package org.alignprivate.asd.tools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class TotalStudentsInAmazonExtractorTest {
    TotalStudentsInAmazonExtractor extractor;

    @Before
    public void init() {
        extractor = new TotalStudentsInAmazonExtractor();
    }

    @Test
    public void extractFromPrivateAndLoadToPublicTest() throws SQLException {
        Assert.assertTrue(extractor.extractFromPrivateAndLoadToPublic() == 0);
    }
}