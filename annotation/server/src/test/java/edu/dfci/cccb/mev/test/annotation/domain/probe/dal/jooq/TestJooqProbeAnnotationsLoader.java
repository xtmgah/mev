package edu.dfci.cccb.mev.test.annotation.domain.probe.dal.jooq;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.dfci.cccb.mev.annotation.domain.probe.contract.ProbeAnnotationsLoader;
import edu.dfci.cccb.mev.annotation.domain.probe.jooq.JooqProbeAnnotationsLoader;
import edu.dfci.cccb.mev.annotation.server.configuration.ProbeAnnotationsPersistanceConriguration;
import edu.dfci.cccb.mev.test.annotation.server.configuration.ProbeAnnotationsPersistanceConrigurationMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ProbeAnnotationsPersistanceConrigurationMock.class})
public class TestJooqProbeAnnotationsLoader {
  
  @Inject @Named("probe-annotations-datasource") DataSource dataSource;
  
  @Test
  public void testLoadUrlResource () throws SQLException, IOException {
    URL url = TestJooqProbeAnnotationsLoader.class.getResource ("/array_annotations/from_affymetrix/HT_HG-U133A.na33.top3.annot.out.tsv");
    assertNotNull (url);
    
    ProbeAnnotationsLoader loader = new JooqProbeAnnotationsLoader (dataSource);
    
    loader.loadUrlResource (url);
  }
  
  @Test
  public void testInit_Force() throws SQLException, IOException, URISyntaxException{
    
    //this folder has a file in it which tells the loader to reload the database: 'reload.flag'
    URL url = TestJooqProbeAnnotationsLoader.class.getResource ("/array_annotations/from_affymetrix_force/");
    assertNotNull (url);
    
    ProbeAnnotationsLoader loader = new JooqProbeAnnotationsLoader (dataSource);
    URL fileUrl = TestJooqProbeAnnotationsLoader.class.getResource ("/array_annotations/from_affymetrix_force/HT_HG-U133A.na33.top3.annot.out.tsv");
    assertNotNull (fileUrl);    
    
    assert(loader.init (url, "*.annot.out.tsv")>0);    
  }
  
  @Test
  public void testInit_NoReload() throws SQLException, IOException, URISyntaxException{
    
    URL url = TestJooqProbeAnnotationsLoader.class.getResource ("/array_annotations/from_affymetrix/");
    assertNotNull (url);
    
    ProbeAnnotationsLoader loader = new JooqProbeAnnotationsLoader (dataSource);
    URL fileUrl = TestJooqProbeAnnotationsLoader.class.getResource ("/array_annotations/from_affymetrix/HT_HG-U133A.na33.top3.annot.out.tsv");
    assertNotNull (fileUrl);    
    
    assert(loader.init (url, "*.annot.out.tsv")==0);    
  }
}