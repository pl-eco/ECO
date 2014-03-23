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
        final public static long jlc$SourceLastModified$jl = 1395439437918L;
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
        
        double testvar = 0;
        
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
                    public double calibrate(double input) {
                            int bLeft = BatteryInfo.getRemainingCap();
                            double sratio = (budget - (bInitial - bLeft))/budget;
                            double dratio = (double)(XSLTBenchEco.this.todo)/(XSLTBenchEco.this.todo + XSLTBenchEco.this.finished);
                            if (sratio > dratio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                            else if (dratio > sratio * 1.1 && mode > 0) --mode;
                            return input;
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
                        $calibrator.calibrate(finished += (quality[$calibrator.getMode()]));
                        $calibrator.calibrate(todo = (quality[$calibrator.getMode()]) * _workQueue.size() * (quality[$calibrator.getMode()]));
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
        final public static long jlc$SourceLastModified$jl = 1395439437918L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAL1Ye2wcRxmfe9iOz0Y+O7bTpmkcO05pHtwSICXYkdrUPjcX" + "n2vXr7iOm+t4d863\n9t7uZnbOvrihD1VqQqMCaYqUCvL4I1JC1dJKBRWkUl" + "K1hdIIqSBUJKQGUCRAgiIhJIgEf/DN7O7t\n7frOKTSqpZ2bnZnv/X2/+dYv" + "foRqLIo2yFaCHTGJlegbG8HUIkqfhi1rHJYy8js19SMXB3UjjEJp\nFFYVhp" + "rSsiUpmGFJVaRUf2+Rou2moR2Z0wyWIEWWmNd2Ofz2p3etYHjg7GstT1yIdo" + "RRTRo1YV03\nGGaqoSc1krcYiqfn8SKWCkzVpLRqsd40+gzRC/k+Q7cY1pl1" + "GD2KImlUa8qcJ0OdaVe4BMIlE1Oc\nl4R4aUSIBQ5rKWFY1YmytyQOKHf4KU" + "Fth2505WlgsoZvToI5QgOwelPJatvaFaaakUuTdx09/90I\nappGTao+xpnJ" + "YAkDedOoMU/ys4RaexWFKNOoWSdEGSNUxZq6LKROoxZLndMxK1BijRLL0Bb5" + "wRar\nYBIqZLqLadQoc5toQWYGLfkoqxJNcd9qshqeA7PbPbNtcwf4OhgYU0" + "ExmsUycUmiC6oOEe8IUpRs\n7B6EA0BalycsZ5RERXUMC6jFjqWG9TlpjFFV" + "n4OjNUYBpDC0vipT7msTywt4jmQYuiV4bsTeglP1\nwhGchKG24DHBCaK0Ph" + "Clsvhsb//n8UvfeeMekdtRhcga1z8GRBsDRKMkSyjRZWITXi8knks9WNgQ\n" + "RggOtwUO22f2bnltIv3nn3TYZ26rcGZ4dp7ILCPff7Jj9JH7DBThaqwxDUvl" + "wfdZLsphxNnpLZpQ\nte0ljnwz4W5eHv3pg4+/QP4SRrEUqpUNrZCHPGqWjb" +
           "ypaoTeR3RCMSNKCtUTXekT+ylUB/M0pLy9\nOpzNWoSlUFQTS7WGeAcXZYEF" + "d1E9zFU9a7hzE7OcmBdNhFAIHtQDz0Fk/4lfhnb298yAj1XTIjNL\nBl2wIM" + "hkJikbMxaVZxix2MzUWHr8XvBzDlYThJkMfUHq75EcKqlEJcG+BFQSp5ICVE" + "WuSstSKMSx\nLVijGlDvMzSF0Ix88dp7R5ODXztux5/nrGME+L6cZ/cUhhw+" + "AMIJRaGQYNzKU9t2PThuAUoQwKpx\n69hD+x8+3hWBmJtLUe4LOCpVR8c+r2" + "hTAt9kyJyG91+6cv4fjdvCKFwZuriWAJ4xzmaE410JkrqD\nyV6J/9+eHnr1" + "gysf3umlPUPdK6pxJSWvpq6gP6khEwXwyWN/4damyAE0eTKMolCiAEtCf6j4" + "jUEZ\nvqrqdRGK21KXRg1Zg+axxrdcWImxHDWWvBUR6LiYrwVf1/M0a4VHdv" + "JO/PLdNpOP7XZi8OAFrBAI\neP3J2s//5vWGd4RbXLBsKruOxgizS6/Zi/04" + "JQTWPzw9cupbHx07KALvRJ7BHVWY1VS5CCR3eCRQ\ncxrUPQ9k94SeNxQ1q+" + "JZjfAE+k/Tlp0/+OvX43ZoNFhxI7vjxgy89VvvRY9fOfSvjYJNSOaY75nh\n" + "HbOtWetx3kspPsL1KD7xq9uf/xk+A5AEMGCpy0RUdlhYFhYeXwdcfUXC6+OB" + "AikQ4HlLeUtB1TxA\n06KI8rWnun787sS5YzYqbl2lbyinysiP/e73C5FvvD" + "lr0wXhOXD45MYLf3z12mir7UX7Dtu84hop\np7HvMZEfTSaPV+dqEsTpt7d3" + "vvjo6FVHoxY/GiehY/nTkbfIZ/c884cK6BKBm1YISwhXbhXj53i2\nCgcjsd" +
           "fLh07QZUcVL1VoUzLy0RfmugqHf/4jIbUBl/c75bk8hE3b3DgfurjJ64KItg" + "9bOTj3pcv3\nz8S1y/8GjtPAUYb2wBqmAKBFXyU4p2vqfvvmW+0P/zKCwgMo" + "phlYGcACRFA9VC+xcoC9RfPue0SB\nNi6tgTEuTEbCCesdBxTL3qLWqpkywJ" + "scD34yszsupd8bPiMcUBU9KyRRgM/yGxNnr/+CXRV8PBjj\n1JuKKy8WaAw9" + "2t0fLDbXvnIuH0Z10yguO63rJNYKHCymodOy3H4W2lvfvr9rsluE3hJMbwhm" + "cZnY\nIIB6KQdzfprPGwOYGebe5pNDDmYeCmJmCIlJSpBsFuMdphMlgLjMYV" + "HyqyOCjbt83MOH/XZ0766a\nBf1+/WLwYEc/XEW/UT4MQmllVIVPdwVkjv2P" + "MtvgyTkyc1VkHnBk1vEuZNH5Mthp2pLuAucoBlwA\nQeunbqgJH1pdIRk+bG" + "GooXtouD+ZGUgl0/1QEG3EzbwhQyGT4zby4Yn+Z4uP/Hpf2MEQUIb/7A6o\n" + "0LqKCoJgsKz8Qm5wO3leFhPFvJZgFOsWv6ATSUoNyq8M3lhyGLm9Wr8tUPPY" + "1N8bn8JvP+Qq+GXw\nkvMZ5C/4bdULfkh8a3jVtq3n/HJHz8sn/o9+qSOgbJ" + "B18+JtD0Ry6rth8VVj1+CKryE/Ua+/8mKg\nT4Hq47766/D3LHwy7+TafMWe" + "ReTAKncFXWVPtBAGVAYt6MEMjS4aquLlhXmj1HThWbxofiu+CM8p\nx4pTH9" + "uKkD/H7qyUY+PujNBkUSYmD6rg+NjNZPYkH44yVEN4SvOXRc8zX/0kntkNz2" + "nHM6c/Hc88\nczOZneTD0wzFsphhLVnBPSc+iXt2wXPGcc+ZT8c9376ZzM7x" +
           "4TRcA0uY6qo+F/DN8x/XN3CdNpR9\nabrKNJbfqaa7GhedF28SEuPQV2Gl6O" + "PFG/vNPggV/xhzYa5g/2ssI0+9dHBT8cT4NwV21sgaXl7m\nbGJpVGd/f5Wg" + "srMqN5fX++iVlydf/95XXGyP21eNd/f4kuCGFxFY0FH5oyeZN5n4TFn+4brv" + "77l4\n9mpYfHb9F8ZBeTfPFAAA");
    
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
    final public static long jlc$SourceLastModified$jl = 1395439437918L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVZf3AUVx1/uSRcEoL5AYQfhSRAUgqkd5ARLaSODflRAheS" + "Xn5BCB6bvXfJkr3d\nZfdd2KRMbYdOQWtrsdXRkVJGYaBIpTPVQZ0WwbYWwT" + "q1WphxhlSHGXWqbe04o4z2D7/ft3u3d3u3\ngSAz+7K3+77f9/n++rzvW858" + "SAoNnSwRjQCb0KgRaOnpFnSDRltkwTB64VFEfLOwuPvkVkX1kbwQ\n8UlRRs" + "pCohGMCkwIStFgR2uTqZM1mipPjMgqC1CTBfbI6219W0LrsxQOHD1X+djxgh" + "ofKQyRMkFR\nVCYwSVXaZBo3GCkP7RHGhWCCSXIwJBmsKUTmUCURb1EVgwkK" + "M/aSR0h+iMzSRNTJyLJQcvEgLB7U\nBF2IB/nywW6+LGiYq1MmSAqNNqeWA8" + "mGTEmAbcuFs2eDkiJ82Q/mcARgdW3KasvaLFO1/FP9n9t/\n7MV8UjZIyiSl" + "B5WJYAmD9QZJaZzGh6luNEejNDpIKhRKoz1UlwRZmuSrDpJKQxpRBJbQqRGm" + "hiqP\n48RKI6FRna+ZfBgipSLapCdEpuopH8UkKkeTvwpjsjACZlc5Zlvmtu" + "NzMLBEAmB6TBBpUqRgTFIg\n4jVuiZSNdVthAoj645SNqqmlChQBHpBKK5ay" + "oIwEe5guKSMwtVBNwCqMLPZUir7WBHFMGKERRha6\n53Vbr2BWMXcEijAy3z" + "2Na4IoLXZFKS0+a6r+dejUkfMP8NwuiFJRRvwlIFTtEgrTGNWpIlJL8GYi\n" + "8FzHjsQSHyEweb5rsjWnuf5cX+ivP6+x5tyVY07X8B4qsoi47XBN+OEHVZKP" + "MIo01ZAw+BmW83Lo\ntt80mRpUbVVKI74MJF9eCP9yx6On6d98pKSDzBJVOR" +
       "GHPKoQ1bgmyVR/kCpUFxiNdpBiqkRb+PsO\n4of7EKS89bQrFjMo6yAFMn80" + "S+W/wUUxUIEuKoZ7SYmpyXtNYKP83tQIIX64SClc9cT6x/8ysq51\n4xD4WN" + "IMOrRP1ccMCDIdahPVIUMXhxg12ND2nlDvJvDzKDwNUKYx0hhs3Ri0pYIpqS" + "C8D4JUEKWC\nLikToVTuy8tDbnPXqAzSm1U5SvWIePLG5f1tW79yyIo/5qxt" + "BCOl6TpJXh5XNg/T2XI3OGsMyg4I\nqnRVz64tuw8tz4c4a/sKwFKcGvRmxB" + "anUDs4p4mQLbPfeenKsX+WrvYRX266QmRAmCWophs5LkVD\nde4Ez6X/o692" + "vnL1yvV7nFRnpC6rArMlsYKWu32oqyKNAic56o8vKssfIP2HfaQAyhKoiOOH" + "Kq92\nr5FRSU1JVkJb/CEyO6bqcUHGV0kqKWGjurrPecKDW87v54KvizC15s" + "F1xs41/hffztdwrLKSAYPn\nsoKz3s0Ds9Zee3X2m9wtSYIsS9uCeiizyq3C" + "iX2vTik8v/7t7me/+eHBnTzwduQZ7EuJYVkSTRC5\n2xGBOpOh1jGQdX1KXI" + "1KMUkYlikm0Kdl9et+/Peny63QyPAkGdmGWytwni/aRB698qV/V3M1eSLy\n" + "vGOGM82yZq6juVnXhQnEYT727tLvvCU8DzQEpW9Ik5RXc56d0whqARQGl5TU" + "QDtwAXdugL9axcd7\nswWspXAPCLSZItUQBJdrxGEZgGnwqJUc23BE3H96ZH" + "li769+ys2cLaTv5+lx6xS0JitVcFiO4Vjg\nrt7NgjEK8z57YdtQuXzhv6Bx" + "EDSKsP0ZXToQhJkRdXt2of8PF1+v2v3bfOJrJyWyKkTbBV4wpBgy\nlRqjwC" +
       "2m9sUHeDKW7sP8LMdbk3CHLObOIaaZ9qsUwK3y5ot23MSdUosMN5wKXe56nj" + "vAkyly7GEu\nPZPn+47efJtNcT1OyaJ0rZlNnND4OLL3XR2vmPXyC3Ef8Q+S" + "ctFuzfoFOYGFMQidhJHs16B9y3if\n2RVYW2BTipKWuOkibVk3WTiEDfc4G+" + "9LXfzA96IauFba/LDSzQ+c2SucJN2kqjIVlLdr9UffO/Kf\nj3hWFI4jdCgH" + "FNnEla/g492pui+MSYogm4z4x6k+rBoUtC5Mb6x1KQ4b9DjnvRtPLH/tUt8L" + "B63e\nYJrYZ0hFxC+//8ex/K9fHLbk3AF2TT5cffzPr9wIz7N4xerkVmQ1U+" + "kyVjfHPVimYcksm24FPvuN\nNcvOPBKeshFVZvYkbdC3/2Xidbry/qf+lGOP" + "9Q9brrYYGsf1OLRYtXGfZw19IRXdYny6CK5VdnRX\nZUWX8Ju+nDHza7o0Lm" + "AzDgmkRlX+eotmrd8FXB5VgcypC1//DPEthWu1jW+1B77dOAwwUgRZJBmj\n" + "NIq/d7oWFm5/4bLkwmvshdd4LEyzHePD+3vAeoMfV8A5hRBoarq9kw/HBRfC" + "2AwRboGrwUbY4IFQ\ns13j35uAgxGbwK2cOkcuBocJBdPxWx/8YO2nV+XrVq" + "INC0YaH6ShLhhXJe7dMRf2vTPEvgGuoI09\nmE0qcJLdLgCh1G4BZqltDHw+" + "sA5fTXj4ezMOO8DXc7Y3h5q3Rfrbwj0dXduSm2i5w0/WQcoFfnKG\n4JfAtd" + "YGv9bD8QduBdVviHCUEEdztgYugI/fPsA8fDoHrkYbYKMHwK9lAyQIC08J1D" + "oEu4P81O3D\n8Cf9dMKGccIDxrM4PM1IcYTRuCYDn3AdhsZTL5H0zlL0jhkw" +
       "43KA6YJiYKsb6E2XSMP53AzdhaV+\n0sZ50gPnd22cJRF00EMJmqBJbFXpp5" + "26geRrF6gjMwSF2+5pG9RpD1Dfs0EVReyoYXmnbZu8O8Xy\nfvGZ1rnhDTsP" + "8ANSMTCBYGxz9n+fVdN5sAfVe++mKWURceWuc/+4eJ6u5Nt7kWRAn9Gsj+T4" + "XJAm\n84lwmnZeix3lhxyHYdzfWbI/o2R8HeFZ8ZlMT5VO56lkkBZmBImTyw" + "D3maZpcLr2c5+sa9RcUfv+\nLaOGw7xkQM7iUM/I7LrOrta2SHtHW6gVYjI/" + "RbmdapT291oeEfpav2E+/PvNuPvbPIt/ZBeEedNA\n4AL1Gb0xLLfU66MNbz" + "oObv+k9AnhjV3JZbuh+Jiq3SvTcSqnqSoATau906GTR8XpaldvPDZZs/Hs\n" + "k3dwBq9x4XWrrhi/66H8UemSj38ds3rdrK9qmUJNmR1uCeBJWDuds6/VZHca" + "YTuNwu404jHhe6nr\nvEZst+PvC9O8+wUOr0FmjFDWrasjcNjh83Y6gT5/q1" + "xLhRh//CzzFI+d+ZSNfsoTfX3u0yYnez7r\n8jQm/BqHt2CTFXUKrDtgU05a" + "6iYcYy79P8ZUw/WxbczHd27Me9OfraudtqADSUdPaIxGM4/Z13B4\nlzezaC" + "/++o1j5O/u0MgyO2J5lZaN1t8sI726SxwkcxrjTqRWwvYetxLvlTybJIsYiy" + "xiXL/eivCN\nO/xc8QEOU9BAxoEWXF58/3a9aLq+LGpaDvDWodjMEMSPNisy" + "qIz/R0eSbhLWf3VExO0v7aw1n+x9\nhnNYoSgLk5OopgQ2IevbWoqylnlqS+" + "p6h7x8tv/VH25I0my5xeVOYDNy45ZMDxbU5P6g1RbXGP8E\nNfmTBT+6/+TR" +
       "KR//pPY/yKJevJ8aAAA=");

    public XSLTBenchEco $SET_MODE(int mode){  this.$MODE_FIELD = mode; return this; }
    public int $GET_MODE() { return this.$MODE_FIELD; }
}
