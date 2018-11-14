import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: heyc
 * @Date: 2018/6/9 11:52
 * @Description:
 */

@Service
@Transactional(readOnly = true)
public class Skylinetest {
    public  static List<Point> readSHP(String path){
        ShapefileDataStore shpDataStore =null;
       List<Point> pointList = new ArrayList<Point>();
        try {
            shpDataStore = new ShapefileDataStore(new File(path).toURI().toURL());
            shpDataStore.setStringCharset(Charset.forName("GBK"));
            String typeName = shpDataStore.getTypeNames()[0];
            FeatureSource<SimpleFeatureType,SimpleFeature>featureSource =null;
            featureSource = (FeatureSource<SimpleFeatureType, SimpleFeature>)shpDataStore.getFeatureSource(typeName);
            FeatureCollection<SimpleFeatureType, SimpleFeature> result = featureSource.getFeatures();
            System.out.println(result.size());
            FeatureIterator<SimpleFeature> itertor = result.features();
            while(itertor.hasNext()){
                SimpleFeature feature = itertor.next();
                Collection<Property> p = feature.getProperties();
                Iterator<Property> it = p.iterator();
                while (it.hasNext()){
                    Property pro = it.next();
                    if (pro.getValue() instanceof  Point){
                        System.out.print("PointX = " +((Point)(pro.getValue())).getX());
                        System.out.print("PointY = " +((Point)(pro.getValue())).getY());
                    }if (pro.getValue()instanceof MultiPolygon){
                            MultiPolygon mp = (MultiPolygon)(pro.getValue());
                            System.out.println(mp.getCentroid());
                            pointList.add(mp.getCentroid());
                    }else {
                        System.out.println(pro.getName()+ " = " + pro.getValue());
                    }
                }
            }
            itertor.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  pointList;
    }





























}
