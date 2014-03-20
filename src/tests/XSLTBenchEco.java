import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import tool.FileWriter;
import tools.BatteryInfo;
import java.util.Map;
import java.util.HashMap;

class $UTILMODES{  
		public static final int $MAX = 2;
		public static final int full = 2; 
		public static final int high = 1; 
		public static final int low = 0; 
} 

public class XSLTBenchEco {
    final boolean verbose = false;
    private double todo = 0;
    private double finished = 0;
    public static int index = 2;
    private int[] quality = new int[] {58, 74, 104, };
    final private String XALAN_VERSION = "Xalan Java 2.7.1";
    final private File scratch;
    int workers;
    class WorkQueue {
        java.util.LinkedList<String> _queue = new java.util.LinkedList<String>();
        
        public synchronized void push(String filename) {
            if (verbose) System.out.println("workQueue.push");
            _queue.add(filename);
            this.notify();
        }
        
        public int size() { return _queue.size(); }
        
        public synchronized String pop() {
            while (_queue.isEmpty()) {
                try {
                    this.wait();
                }
                catch (InterruptedException e) {  }
                if (verbose) System.out.println("workQueue.pop");
            }
            return _queue.removeFirst();
        }
        
        public int $MODE_FIELD;
        
        public WorkQueue() { super(); }
        
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1395268068867L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAKUYa2wcR3nuzo/40foRP1onjR91CInNXRISmtotxLHPiZtz" + "7PqV1L1wWe/O2Rvv\n7W52Z+2zG9qUoiaQEhoeoSkkzY9ICVVLKwoqoFJSpY" + "HSACoIBalSU1AQVJQiISSIBCp8M7N3+7g7\nB1pLNzc3833ffO+Hn3kPFZsG" + "WimaYbKgYzPcOzosGCaWehXBNMfgKCFeKi4bPrdT1YIoEENBWSKo\nKiaaEU" + "kgQkSWIgN93WkDdeiasjCtaCSM0yS8X9ls07sntjmH4O7TL9Y+fLaoOYiKY6" + "hKUFWNCETW\n1KiCUyZB1bH9wpwQsYisRGKySbpj6CasWqleTTWJoBLzAHoQ" + "hWKoRBcpTYJaY5nHI/B4RBcMIRVh\nz0eG2bNAYbmBiSCrWOrJPgeYnV5MYN" + "vGG8mFBiLL6OUEiMM4AKlbslJzaXNE1UPnJz5x8My3Qqhq\nElXJ6iglJoIk" + "BN6bRJUpnJrChtkjSViaRDUqxtIoNmRBkRfZq5Oo1pSnVYFYBjZHsKkpcxSw" + "1rR0\nbLA3M4cxVClSmQxLJJqR1VFSxoqU+VWcVIRpELvBEZuL20/PQcByGR" + "gzkoKIMyhFs7IKFm/2Y2Rl\nbN8JAIBamsJkRss+VaQKcIBquS0VQZ2OjBJD" + "VqcBtFiz4BWCmgoSpbrWBXFWmMYJgm7xww3zK4Aq\nY4qgKATV+8EYJbBSk8" + "9KLvt0NPzjyPlvvryV+XaRhEWF8l8OSKt8SCM4iQ2sipgjXrfCXx24z1oZ\n" + "RAiA633AHKZn9YvjsXd+3MxhVuSBGZraj0WSEHcdbx55YLuGQpSNZbpmytT4" + "HslZOAzbN91pHaK2\nIUuRXoYzlxdGfnLfoafxu0FUPoBKRE2xUuBHNaKW0m" +
           "UFG9uxig2BYGkAlWFV6mX3A6gU9jFweX46\nlEyamAygIoUdlWjsN6goCSSo" + "ispgL6tJLbPXBTLD9mkdIRSADyqBzx2I/7Fvgjb0dcVBx7Ju4vi8\nZsyaYG" + "Qcj4pa3DTEOMEmie8ZjY1tAz3PwGkYE52gjZG+roiNFcliReA+AlgRihXxYa" + "UpK7XzgQDN\nbf4YVQB7h6ZI2EiI5669fjC68/NHuP2pz9pCQJC4abbvhnfv" + "tbCFUSDAyNZRx+aKB7XNQgBCqqpc\nO7r3nn1H2kJgcX2+iGoCQCOFc2OvE7" + "IDLLuJ4DcVbzx7+czfK9cFUTB/4qI8Quosp2SGabbLJqR2\nv6vno//XLwy+" + "cOXyWx91nJ6g9pxYzMWksdTm16ahiViC7OSQP3trVWg3mjgeREUQoJCUGP8Q" + "76v8\nb3hiqjuTn6gspTFUkdSMlKDQq0xSKSczhjbvnDAzV9Oljluc2sXHIE" + "tt1x8pWf/blyouMYkzWbDK\nVWdGMeExVeOYdczAGM7femL4K1977/D9zKbc" + "qCgNkB9xICGGFIhjapr2cTWlSXJSFqYUTF3i31Wr\nN3zvL8equbIVOMnYqv" + "PGBJzzW7ehQ5c//c9VjExApDnc4d4B40Isdyj3GIawQPlIP/zr207+VDgF\n" + "KQbC2pQXMYtUZAtEmepkWl3D1g7fXZgurUC7s4Az56mYCfHg09Nt1oGf/YBx" + "XSG4S69b+4OC3s1t\nyd5eDo/WIHvxZBB6W6/TtYGaoNEfgzsEcwaIbbqwK1" + "6tXPgXPDsJz4pQzswhAwI+7TGwDV1c+uYr\nFxv2/SqEgv2oXNEEqV9gbo/K" + "wN+wOQO5Iq1/aitjo3J+GazVTC+Icdtkaynt+hUE5tYWjvp+WpSd\ngElMdZ" +
           "6PvT50immpYLznqUk+Oosvj5++/gtyldFxAo9it6RzEyE0Mg7ulitzNSXPP5" + "UKotJJVC3a\nrdaEoFg0BiahMzAz/Re0Y557b5XnJa07m1hW+oPe9aw/5J0E" + "DHsKTfeVPs8IZrxhi+0ZW/yeEUBs\nczdd2gkqSRygiRtMssLV5o5aUyZxdQ" + "Kbt97R+Z0d8Yss6ZZBAyaYuxxuoO2luwCocV1hu/ppGhfH\nLuFr9Sd4A+B1" + "CNal2qh+vBNr353rWP/ZU4yXoinBZGyUg7ZMCklQS+GOl9Hi0XQT19l/4A/B" + "5336\nobpiB6weN7k77UwRC7O2Wte5QzeS/KWO3m0Hndb6dZoQG+P6+AR6LM" + "7Fbr2BvhJi089/eGX3mxM6\n89uqORnaGCyN2a22N9M573d72u+8Gk2If2jb" + "e/XJordPsr6KK4/yvR6yJP3elBPUtFw3uiSyuWDJ\n+M+/PFn3OeOIxqxSzD" + "wk1ztWF5DWTSghHntn2/uHYrNrgtTry2mgCAY0ZNDxhQtNJG4C7WOw6wMs\n" + "CKCbOTZ01syBbEepzZ5mCzRBHytEmw5p/jruj01IjPPY2KZZKpO1xRut5Zau" + "u2+Z/938If1Pp38E\nBaIZR6xmjkjTTJinGYJKmR023qnngeEDhycxozRByx" + "jKhk0bdd4z0DVCl09yyI8XTPVbeKth55d9\n7KyNravtbg+yjW5NKbII71S0" + "Dw71RRP9A9FYH7hVPc4k3kFNwhNjPNSF8b4vpx/4zQ4eK7e453BD\nTkE/P8" + "eao2uPtv3otfGnDufLJN5h242VEB96+3ezoS+9MsXx/PXDB3x81dk/vnBtpI" + "63Knzwuz1n\n9nLj8OGPGbtKpxW5dakXGPSrHa3PPDhy1eao1jvCRGHM/9PC" +
           "Rbzmri/+Pk9LHoLx1GezuiVsxvhq\n91gfWLyt0DTI2Du852+Vjwqv7g3afQ" + "9UsBJ7SHfohMwly8Agm4Sd2rqu68xic9dzRz9AP9/sY9ZP\numZuxb2hGfm1" + "IJu5ecXNmdW9SN2+yAV+LEMd81Tb5my1baBqWw+fLrvadvmrrWMQTygE6R76" + "1Epz\nAUYoQ1Oh45TSvg4zYM9IBWOXXjy0RFv6CF0WCMyfljnDIJJ23ZqFwz" + "lNlhxXWbxReGedhP6Yy6qg\njB7STY+tgp68KqCLsASrx5a4e5wuRwnvyzNi" + "0K+Uw/5jH5B9ZsE2+ERt9qP/lwXp8hm/3dy8f2OJ\nu1N0OQGBq2uFE7Qj4t" + "f/VxEhu5ZlJ/IM4Ur3wJ7vOV4zvOWAjku3F2wjBi3+D8SEuOfZ+1vSR8ce\n" + "58UfKtTiol1qS/mcmg3Z1oLUMrTeQM8/N/HSt+/M5BjPBJtjyRumOZCgOf8o" + "GU3phA1/i99v/O5d\n505fDbIZ9r+4xnf/9RUAAA==");
    
        public WorkQueue $SET_MODE(int mode){  this.$MODE_FIELD = mode; return this; }
        public int $GET_MODE() { return this.$MODE_FIELD; }
    }
    
    
    private double getProgress() { return (double) finished / (double) (todo + finished); }
    
    class XalanWorker extends Thread implements ErrorListener {
        WorkQueue _queue;
        int _id;
        
        public XalanWorker(WorkQueue queue, int id) {
            super();
            _queue = queue;
            _id = id;
        }
        
        public void run() {
            try {
                if (verbose) System.out.println("Worker thread starting");
                FileOutputStream outputStream = new FileOutputStream(new File(scratch, "xalan.out." + _id));
                Result outFile = new StreamResult(outputStream);
                tools.Calibrator $calibrator = new tools.Calibrator() {
                    public int mode = $UTILMODES.$MAX;
                    private double budget = 12345;
                    private int bInitial = BatteryInfo.getRemainingCap();
                    public int getMode() {
                            return mode;
                    }
                    public void calibrate() {
                            int bLeft = BatteryInfo.getRemainingCap();
                            double sratio = (budget - (bInitial - bLeft))/budget;
                            double dratio = (double)(XSLTBenchEco.this.todo)/(XSLTBenchEco.this.todo + XSLTBenchEco.this.finished);
                            if (sratio > dratio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                            else if (dratio > sratio * 1.1 && mode > 0) --mode;
                    }
                };
                {
                    while (true) {
                        {
                            String fileName = _queue.pop();
                            if (fileName.equals("")) break;
                            Transformer transformer = (_template[$calibrator.getMode()]).newTransformer();
                            transformer.setErrorListener(this);
                            FileInputStream inputStream = new FileInputStream(new File(scratch, fileName));
                            Source inFile = new StreamSource(inputStream);
                            transformer.transform(inFile, outFile);
                            inputStream.close();
                        }
                        finished += (quality[$calibrator.getMode()]);
                        todo = _workQueue.size() * 1;
                    }
                }
                ;
            }
            catch (TransformerConfigurationException e) { e.printStackTrace(); }
            catch (TransformerException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            if (verbose) System.out.println("Worker thread exiting");
        }
        
        public void error(TransformerException exception) throws TransformerException { throw exception; }
        
        public void fatalError(TransformerException exception) throws TransformerException { throw exception; }
        
        public void warning(TransformerException exception) throws TransformerException {  }
        
        public int $MODE_FIELD;
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1395268068867L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAL1YfWwcRxWf+7Adn418dhynTdM4dpzSfHBLgJRgR2pT+9xc" + "fK5dfyV13FzHu3O+\njfd2N7Oz9sWJSkvVJqUCagoFVJrmj0gJVUsrFVSQSk" + "nVFkojpIJQkZAaQJEACYqEkCAS/MGb2d3b\n2/WdU9GolnZudua9N+/zN2/9" + "/AeozqJoo2yl2HGTWKn+8VFMLaL0a9iyJmApJ79V1zh6fkg3oiiS\nRVFVYa" + "glK1uSghmWVEXKDPSVKNphGtrxOc1gKVJiqaPablfegezuFQIPnnml7aFz8c" + "4oqsuiFqzr\nBsNMNfS0RooWQ8nsUbyAJZupmpRVLdaXRZ8gul3sN3SLYZ1Z" + "x9ADKJZF9abMZTLUlfUOl+BwycQU\nFyVxvDQqjgUJaylhWNWJsq98HHDuDH" + "KC2i7f2EpqELKGb06BOUIDsHpz2WrH2hWmmrELU7edPPu9\nGGqZRi2qPs6F" + "yWAJg/OmUXORFGcJtfYpClGmUatOiDJOqIo1dUmcOo3aLHVOx8ymxBojlqEt" + "cMI2\nyzYJFWd6i1nULHObqC0zg5Z9lFeJpnhvdXkNz4HZHb7ZjrmDfB0MTK" + "igGM1jmXgs8XlVh4h3hjnK\nNvYMAQGwNhQJKxjlo+I6hgXU5sRSw/qcNM6o" + "qs8BaZ1hwykMbagplPvaxPI8niM5hm4I0406W0DV\nKBzBWRhaFyYTkiBKG0" + "JRqojPjo5/nb7w3dfuELkdV4iscf0TwLQpxDRG8oQSXSYO41U79Y3MvfbG\n" + "KEJAvC5E7NDs2/rKZPYvP+10aG6qQjMye5TILCffvdw5duIuA8W4GmtMw1J5" + "8AOWi3IYdXf6SiZU\nbUdZIt9MeZsXx35274PPkb9GUSKD6mVDs4uQR62yUT" +
           "RVjdC7iE4oZkTJoEaiK/1iP4MaYJ6FlHdW\nR/J5i7AMimtiqd4Q7+CiPIjg" + "LmqEuarnDW9uYlYQ85KJEIrAg3rhOYycP/HL0K6B3hnwsWpaZGbR\noPMWBJ" + "nMpGVjxqLyDCMWmzk0np24E/xcgNUUYSZDn5EGeiWXSypzSbAvAZfEuaQQV4" + "mr0rYYiXBs\nC9eoBtz7DU0hNCefv/LOyfTQY6ed+POcdY0A31fK7DmEIYcP" + "wuGEokhECG7nqe24Hhw3DyUIYNW8\nbfy+A/ef7o5BzM3FOPcFkEq10bHfL9" + "qMwDcZMqfp3Rcunf1n8/YoilaHLq4lgGeCixnleFeGpJ5w\nsleT//cvD7/8" + "3qX3b/XTnqGeFdW4kpNXU3fYn9SQiQL45Is/d2NL7CCaWo6iOJQowJLQHyp+" + "U/iM\nQFX1eQjFbWnIoqa8QYtY41serCRYgRqL/ooIdFLM14KvG3matcMju3" + "knfvnuOpOPHU5i8OCFrBAI\nePXh+k//9tWmt4RbPLBsqbiOxglzSq/Vj/0E" + "JQTW3//26JPf/ODUYRF4N/IM7ih7VlPlErDc4rNA\nzWlQ9zyQPZN60VDUvI" + "pnNcIT6L8tW3f98G9fTTqh0WDFi+zOawvw12+8Ez146ci/NwkxEZljvm+G\n" + "T+ZYs9aXvI9SfJzrUXro1zd/5+f4GYAkgAFLXSKisqPCsqjw+HqQGigSXh/3" + "2MQmIPOGypaCqkWA\npgUR5SuPdv/k7clnTzmouG2VvqGSKyd/8fd/mI997f" + "VZhy8MzyHi5U3n/vTylbF2x4vOHbZlxTVS\nyePcYyI/Wkwer67VThDUb+7o" + "ev6BscuuRm1BNE5Dx/Ln42+QT+79yh+roEsMblpxWEq4cpsYP8Wz\nVTgYib" +
           "0+PnSBLjtreKlKm5KTTz43120f+8WPxalNuLLfqczlYWw65ib50M1NXh9GtP" + "3YKgDd5y7e\nPZPULv4HJE6DRBnaA2uEAoCWApXgUtc1/O71Nzru/1UMRQdR" + "QjOwMogFiKBGqF5iFQB7S+btd4gC\nbV5cA2NSmIyEEza4DihVvMWsVTNlkD" + "c5PvzkZndeyL4z8oxwQE30rJJEITlLr02eufpLdlnI8WGM\nc28urbxYoDH0" + "efe8t9Ba/9KzxShqmEZJ2W1dp7Bmc7CYhk7L8vpZaG8D+8GuyWkR+sowvTGc" + "xRXH\nhgHUTzmYc2o+bw5hZpR7m0+OuJh5JIyZESQmGcGyRYy3mG6UAOJyx0" + "TJr44IDu7ycS8fDjjRvb1m\nFgwE9UvAg139cA39xvgwBKWVUxU+3R06c/ya" + "Z/Kh3RN3kA9bGWrqGR4ZSOcGM+nsAKThOuLFe9hQ\nyNSEgzd4cuDrpRO/2R" + "91K3eXKX72hFRoX0UFwTBUkfQRz6VdPBtKqVJRSzGKdYtfi6k0pQblQM3b\n" + "OV68N9fqcgVWnTr0j+ZH8Zv3eQp+HgLnfnz4J8ZBzPbaZTYsOnw/x7f3nl3q" + "7H3x8f+jS+kMKRsW\n3bpw0z2xgvp2VHxLOJm/4hskyNQXzPcE6GNTfSKQ9Z" + "3BToFPCm5WFap2CiIHVkHoo6vsiU+JOchH\najuugKQQv7cxFF8wVMXPi8K1" + "UtMDRfGiBK34LDzLrhXLH9qKSDDHbq2WYxPejNB0SSYmD6qQuHg9\nhZ3gA2" + "OojvCU5i+67xn7o3hmDzxPuZ556uPxzCPXU9hjfPgSQ4k8ZlhLV3HPwx/FPb" + "vhedp1z9Mf\nj3uevJ7CvsWHJxhqWMRUV/W5kG+WP6xv4BJrqvi+85RprrzJ" +
           "TG81KfodfjWnJqCbwUopIIu301sC\nECr+HeXBnO38QyonH3rh8ObS4xNPCO" + "yskzW8tMTFJLKowfnqKUNlV01pnqx30UsvTr36/S942J50\nrhr/7gkkwTUv" + "IrCgs/qnRrpoMvFxsPSj9T/Ye/7M5aj42Pkf01aZw0UUAAA=");
    
        public XalanWorker $SET_MODE(int mode){  this.$MODE_FIELD = mode; return this; }
        public int $GET_MODE() { return this.$MODE_FIELD; }
    }
    
    Templates[] _template;
    WorkQueue _workQueue = null;
    XalanWorker[] _workers = null;
    
    public XSLTBenchEco(File scratch) throws Exception {
        super();
        this.scratch = scratch;
        Properties props = System.getProperties();
        String key = "javax.xml.transform.TransformerFactory";
        String value = "org.apache.xalan.processor.TransformerFactoryImpl";
        props.put(key, value);
        System.setProperties(props);
        Templates _templateFull = TransformerFactory.newInstance().newTemplates(new StreamSource(new File(scratch, "xalan/xsl/xmlspec.xsl")));
        Templates _templateLo = TransformerFactory.newInstance().newTemplates(new StreamSource(new File(scratch, "xalan/xsl/xmlspecLo.xsl")));
        Templates _templateHi = TransformerFactory.newInstance().newTemplates(new StreamSource(new File(scratch, "xalan/xsl/xmlspecMed.xsl")));
        _template = (new Templates[] {_templateLo, _templateHi, _templateFull, });
        _workQueue = new WorkQueue();
    }
    
    public void createWorkers(int workers) {
        this.workers = workers;
        if (_workers == null) _workers = (new XalanWorker[workers]);
        for (int i = 0; i < workers; i++) {
            _workers[i] = new XalanWorker(_workQueue, i);
            _workers[i].start();
        }
    }
    
    public void doWork(int nRuns) throws InterruptedException {
        for (int iRun = 0; iRun < nRuns; iRun++) {
            _workQueue.push("xalan/acks.xml");
            _workQueue.push("xalan/binding.xml");
            _workQueue.push("xalan/changes.xml");
            _workQueue.push("xalan/concepts.xml");
            _workQueue.push("xalan/controls.xml");
            _workQueue.push("xalan/datatypes.xml");
            _workQueue.push("xalan/expr.xml");
            _workQueue.push("xalan/intro.xml");
            _workQueue.push("xalan/model.xml");
            _workQueue.push("xalan/prod-notes.xml");
            _workQueue.push("xalan/references.xml");
            _workQueue.push("xalan/rpm.xml");
            _workQueue.push("xalan/schema.xml");
            _workQueue.push("xalan/structure.xml");
            _workQueue.push("xalan/template.xml");
            _workQueue.push("xalan/terms.xml");
            _workQueue.push("xalan/ui.xml");
        }
        todo = _workQueue.size() * 10.3;
        for (int i = 0; i < workers; i++) { _workQueue.push(""); }
        for (int i = 0; i < workers; i++) {
            if (verbose) System.out.println("Waiting for thread " + i);
            _workers[i].join();
        }
    }
    
    public static void main(String[] args) throws Exception {
        File scratch = new File("scratch");
        XSLTBenchEco bench = new XSLTBenchEco(scratch);
        bench.createWorkers(1);
        BatteryInfo.startWork(5300);
        FileWriter fw = new FileWriter(10000);
        fw.start();
        long start = System.currentTimeMillis();
        bench.doWork(13000);
        long end = System.currentTimeMillis() - start;
        System.out.println("Work finished: " + end + " in total.");
        fw.done();
        BatteryInfo.done();
    }
    
    public int $MODE_FIELD;
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1395268068867L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVZf3AUVx1/uSRcEoL5AYQfhSRAUgqkd5ARLaSODflRAheS" + "Xn5BCB6bvXfJkr3d\nZfdd2KRMpYNT0NpabHV0pJQRGCi20pnqoE6LYFuLYJ" + "1aLcw4heowo06Vjh1nlNH+4ff7dvf2bu8u\nEGRmX/Z23/f7Pt9fn/d9y0s3" + "SaGhk0WiEWATGjUCLT3dgm7QaIssGEYvPIqIbxUWd5/crKg+khci\nPinKSF" + "lINIJRgQlBKRrsaG0ydbJKU+WJEVllAWqywC55ra1vU2hthsKBI2crHz9eUO" + "MjhSFSJiiK\nygQmqUqbTOMGI+WhXcK4EEwwSQ6GJIM1hcgsqiTiLapiMEFh" + "xm7yGMkPkRmaiDoZWRJyFg/C4kFN\n0IV4kC8f7ObLgobZOmWCpNBoc3I5kG" + "xIlwTYtlw4czYoKcKX/WAORwBW1yattqzNMFXLP9X/ub1H\nX8wnZYOkTFJ6" + "UJkIljBYb5CUxml8mOpGczRKo4OkQqE02kN1SZClSb7qIKk0pBFFYAmdGmFq" + "qPI4\nTqw0EhrV+ZrOwxApFdEmPSEyVU/6KCZROer8KozJwgiYXeWabZnbjs" + "/BwBIJgOkxQaSOSMGYpEDE\na7wSSRvrNsMEEPXHKRtVk0sVKAI8IJVWLGVB" + "GQn2MF1SRmBqoZqAVRhZmFMp+loTxDFhhEYYme+d\n1229glnF3BEowshc7z" + "SuCaK00BOllPisqvrXwVOHzz3Ec7sgSkUZ8ZeAULVHKExjVKeKSC3BW4nA\n" + "cx3bEot8hMDkuZ7J1pzm+rN9ob/+vMaac0+WOV3Du6jIIuKWQzXhRx9WST7C" + "KNJUQ8Lgp1nOy6Hb\nftNkalC1VUmN+DLgvDwf/uW2fafp33ykpIPMEFU5EY" +
       "c8qhDVuCbJVH+YKlQXGI12kGKqRFv4+w7i\nh/sQpLz1tCsWMyjrIAUyfzRD" + "5b/BRTFQgS4qhntJianOvSawUX5vaoQQP1ykFK56Yv3jfxlZ07p+\nCHwsaQ" + "Yd2qPqYwYEmQ61ieqQoYtDjBpsaGtPqHcD+HkUngYo0xhpDLauD9pSwaRUEN" + "4HQSqIUkGP\nlIlQKvfk5SG3eWtUBumNqhylekQ8eePS3rbNXz1oxR9z1jaC" + "kdJUnSQvjyubg+lsuRucNQZlBwRV\nuqJnx6adB5fmQ5y1PQVgKU4N5mbEFr" + "dQOziniZAtM999+fLRf5au9BFfdrpCZECYJaimGzkuSUN1\n3gTPpv/jr3W+" + "euXytfvcVGekLqMCMyWxgpZ6fairIo0CJ7nqjy8oyx8g/Yd8pADKEqiI44cq" + "r/au\nkVZJTQ4roS3+EJkZU/W4IOMrh0pK2Kiu7nGf8OCW8/vZ4OsiTK05cJ" + "22c43/xbdzNRyrrGTA4Hms\n4Kx3a/+M1Vdfm/kWd4tDkGUpW1APZVa5Vbix" + "79UphefXvtP97LduHtjOA29HnsG+lBiWJdEEkXtd\nEagzGWodA1nXp8TVqB" + "SThGGZYgJ9Wla/5sd/f7rcCo0MT5zINtxegft8wQay7/KX/l3N1eSJyPOu\n" + "Ge40y5rZruZmXRcmEIf5+HuLv/u28DzQEJS+IU1SXs15dk4jqHlQGFxSUgPt" + "wAXcuQH+agUf788U\nsJbCPSDQZopUQxBcrhGHJQCmIUetZNmGI+Le0yNLE7" + "t/9VNu5kwhdT9PjVunoDVZqYLDUgzHPG/1\nbhSMUZj32fNbhsrl8/8FjYOg" + "UYTtz+jSgSDMtKjbswv9f7jwRtXO3+YTXzspkVUh2i7wgiHFkKnU\nGAVuMb" +
       "UvPsSTsXQP5mc53pqEO2Qhdw4xzZRfpQBuRW6+aMdN3C21yHDDqdClrue5A3" + "IyRZY9zKNn\n8lzfkVvvsOtcj1uyKF1rZhInND6u7ANXxitmvPJC3Ef8g6Rc" + "tFuzfkFOYGEMQidhOP0atG9p79O7\nAmsLbEpS0iIvXaQs6yULl7DhHmfjfa" + "mHH/heVAPXcpsflnv5gTN7hZukG1RVpoLyTq2+7/3D//mY\nZ0XhOEKHckCR" + "DVz5Mj7em6z7wpikCLLJiH+c6sOqQUHr/NTGWpfisEGPc9678cTS1y/2vXDA" + "6g2m\niH2aVET88od/HMv/xoVhS84bYM/kQ9XH//zqjfAci1esTm5ZRjOVKm" + "N1c9yDZRqWzJKpVuCz31y1\n5KXHwtdtRJXpPUkb9O1/mXiDLn/wqT9l2WP9" + "w5arLYbGcS0OLVZtPJCzhr6QjG4xPl0A1wo7uisy\nokv4TV/WmPk1XRoXsB" + "mHBFKjKn+9SbPW7wIuj6pA5tSDr3+a+BbDtdLGtzIHvp04DDBSBFkkGaM0\n" + "ir+3exYW7nzhMmfhVfbCq3IsTDMd48P7+8B6gx9XwDmFEGhqer2TD8cFD8LY" + "NBFugqvBRtiQA6Fm\nu8a/OwEHIzaBWzl1j1wMDhMKpuO3P/rB6k+vyNesRB" + "sWjBQ+SEFdMK5K3LtjHuy7p4l9HVxBG3sw\nk1TgJLtVAEKp3QTMUtsY+Hxg" + "Db6ayOHvjThsA1/P2tocat4S6W8L93R0bXE20XKXn6yDlAf85DTB\nL4JrtQ" + "1+dQ7H778dVL8hwlFCHM3aGngAfuXOAebh01lwNdoAG3MA/HomQIKw8JRArU" + "OwN8hP3TkM\nv+OnYzaMYzlgPIvD04wURxiNazLwCddhaDz1Eo53FqN3zIAZ" +
       "lwNMFxQDW91Ab6pECs7npukuLPUT\nNs4TOXB+z8ZZEkEHPZKgCepgq0o97d" + "QNOK89oA5PExRuu6dsUKdygPq+DaooYkcNyztl2+TdKZb3\ni8+0zg6v276f" + "H5CKgQkEY4u7//usms6DPag+926aVBYRl+84+48L5+hyvr0XSQb0Gc36SJbP" + "BSky\nnwinaefV2BF+yHEZxvudJfMzStrXEZ4Vn0n3VOlUnnKCND8tSJxcBr" + "jPNE2D07Wf+2RNo+aJ2rHb\nRg2HOU5AzuBQz8jMus6u1rZIe0dbqBViMjdJ" + "uZ1qlPb3Wh4R+lq/aT76+424+9s8i39kD4Q5U0Dg\nAvVpvTEstzjXRxvedB" + "zY+knpE8KbO5xlu6H4mKrdL9NxKqeoKgBNK3OnQyePitvVrlx/dLJm/Zkn\n" + "7+IMXuPB61VdMX7PI/mj0kUf/zpm9boZX9XShZrSO9wSwJOwdjp3X6vJ7DTC" + "dhqFvWnEY8L3Us95\njdhux9/np3j3Cxxeh8wYoaxbV0fgsMPnbXcDfe52uZ" + "YMMf74WfopHjvzD2z0H+REX5/9tMnJns+6\nNIUJv8bhbdhkRZ0C6w7YlJOS" + "ugnXmIv/jzHVcN20jbl598a8P/XZutptCzqQdPSExmg0/Zh9FYf3\neDOL9u" + "Kv37hG/u4ujSyzI5ZXbtlo/c0wMld3iYNkTmHcieRK2N7jVpJ7pZxNkkWMRR" + "Yxrl1rRfjG\nXX6u+AiH69BAxoEWPF788E69aHq+LGpaFvDWodhME8SPNsvS" + "qIz/R4dDNwnrvzoi4taXt9eaT/Y+\nwzmsUJSFyUlUUwKbkPVtLUlZS3Jqc3" + "S9S1450//aD9c5NFtucbkb2LTcuC3TgwU12T9otcU1xj9B\nTf5k3o8ePHnk" +
       "uo9/Uvsf1Xmu2J8aAAA=");

    public XSLTBenchEco $SET_MODE(int mode){  this.$MODE_FIELD = mode; return this; }
    public int $GET_MODE() { return this.$MODE_FIELD; }
}
