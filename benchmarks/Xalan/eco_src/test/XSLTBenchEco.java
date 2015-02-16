package test;

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
    private int[] quality = new int[] { 58, 74, 104, };
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
        
        public WorkQueue() { super(); }
        
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1417475253000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAK0YC2wUx3Xuzh/8AX8AQ4BgbOwkgLkzCAeCI8AGEwxncPyD" + "GJPLenfOXry3u+zO\n2oexKEkjIKFNgpqgUJVPWyoITRratKKt0pSI0KahrZ" + "JIpRJSSCuqNmqaSlWllKpV2jcze7efu3MQ\n7Uk3Nzcz7/+defkTlG8aaJ5o" + "hsleHZvh9d2dgmFiab0imGYPLMXEK/lFnWe3qFoQBaIoKEsElUVF\nMyIJRI" + "jIUqR9Q3PSQEt0Tdk7pGgkjJMkvFtpsvFtjjZlINx+8mLl42fyqoMoP4rKBF" + "XViEBkTW1T\ncMIkqDy6WxgVIhaRlUhUNklzFE3FqpVYr6kmEVRi7kH7USiK" + "CnSR4iSoJpoiHgHiEV0whESEkY90\nMrKAYbqBiSCrWGpJkwPIBi8ksG3DdW" + "WeBiRT6GYfiMM4AKkXpKXm0maIqofO9d0/cfqlECrrR2Wy\n2k2RiSAJAXr9" + "qDSBE4PYMFskCUv9qELFWOrGhiwo8jij2o8qTXlIFYhlYLMLm5oySg9WmpaO" + "DUYz\ntRhFpSKVybBEohlpHcVlrEipf/lxRRgCsascsbm4G+k6CFgsA2NGXB" + "BxCiRvRFbB4tV+iLSMdVvg\nAIAWJjAZ1tKk8lQBFlAlt6UiqEORbmLI6hAc" + "zdcsoELQnJxIqa51QRwRhnCMoNn+c518C04VMUVQ\nEIJm+o8xTGClOT4rue" + "yzpOrTw+e+9sY65tt5EhYVyn8xAM33AXXhODawKmIOeMsKP9/+iDUviBAc\n" + "nuk7zM+01F/sjX7002p+Zm6WM9sGd2ORxMStR6u79j2koRBlY4qumTI1vkdy" + "Fg6d9k5zUoeorUpj\npJvh1Oalrp89cuA8/jiIittRgagpVgL8qELUErqsYO" +
           "MhrGJDIFhqR0VYldaz/XZUCPMouDxf3RaP\nm5i0ozyFLRVo7D+oKA4oqIqK" + "YC6rcS011wUyzOZJHSEUgC8qgO8KxD/sl6COSK8J7h4RREGVVS0y\nJEPgiV" + "okHJFwXLAUEksK4CsR0xAjmjEEOUYUdC3CF3d0R3tawQLDbaIWpm6lE7QlB8" + "I7QZek/FeO\nBQI0IfoDW4GY2KQpEjZi4tmb70y0bXnqMHca6ui25ATNJdgk" + "YTfquu2aMfKwhS2MAgGGewYlxk0G\nCh+B0IUkV7qoe9fmxw7XhsBX9LE8qk" + "M4Wguy2BwAqvVOfLezVCiCk83+xs5D4Vtn13Ini+ROw1mh\nS9595erpv5cu" + "DqJg9hxJJYMsXUzRdNLEms59df6oyob/r093vHbt6gf3OfFFUF1G2GdC0rCt" + "9dvA\n0EQsQSJ00J+5qyy0HfUdDaI8yAWQ/xj/kFrm+2l4wrc5lQqpLIVRVB" + "LXjISg0K1U/iomw4Y25qww\n5yinwwzuJ9SQPgZZFr31xYLG375ecoVJnEq4" + "Za6S1o0JD98Kxw96DIxh/YMXO7/ywieHdjIn4F6A\nknDyHuckhKsCKYOapq" + "5XTWiSHJeFQQVTH/p3Wf2yH/zlmXKubAVWUrZq+HwEzvpdrejA1Uf/MZ+h\n" + "CYi0XDjcO8e4ENMdzC2GIeylfCQff//u4z8XTkA2gwxiyuOYJQVkC0SZWsq0" + "eh8bG3x7ETrUAu6G\nHM6cpTjHxInzQ7XWnl/8iHFdIrirvFv7HYLezG3JaE" + "8HohXIHjzJiu7O1OlYRU0wyx+0mwRzGJCt\nuLR1oFy59C8g2w9kRaic5jYD" + "0kTSY2D7dH7h9TcvVz32XggFN6JiRROkjQJze1QE/obNYcgwSX3t\nOsZG+d" +
           "gUOjK9IMbtHFtLSdc/miYW5Y76jbT+OwETG2w4F31n2wmmpZzxnqX8+fCMv9" + "F78tavyA2G\nxwk8Cl2TzEyf0DM5sKuujVYUXDiVCKLCflQu2l1dn6BYNAb6" + "oQkxU60edH6efW9DwatnczqxzPMH\nvYusP+SdtA1zeprOS32eEaTaXgnfJt" + "szmvyeEUBssoYO9QQVxPbQTA8mmevqqLutQZO4mo6mdSsb\nvrdp4DJLukXQ" + "6wnmVocb6LDpLABqXJzbrn6cxuWeK/jmzGO8DHgdgjXENqgf7tiij0eXND5x" + "gvGS\nNyiYjI1i0JZJTxK0IHdzzXDxaJrKdfYf+CD4fka/VFdsgZX+Oe6mPl" + "X1wqyD13Xu0LNI9tpI9zaB\nTiv9Oo2Jswb03j70pQEuds3n6Csmzvnlj69t" + "v96nM78tG5WhY8JSj93VezOdQ7/Z0+ln1WhM/EPt\nrhtfzfvwOGvhuPIo38" + "sgS9LfJjuoS9NBTQN3lksimwuWjP/86+MznjQOa8wq+cxDMr2jPoe0bkQx\n" + "8ZmPWj87EB25N0i9vpgGimBA7wfNZTjX5ceNoK4HZhsACgJoGoeGJp45kO0o" + "lenVdIEmaGku3PQ+\n6K/j/tiExDiGjVbNUpmsNd5oLbZ03b3L/G/a/+h/Ov" + "0QFGhLOWI5c0SaZsI8zRBUyOyw/AE9yxl+\nt/EkZpQkaAoDWbZ8ue299M4c" + "liU7m254f+Pg+bh6XgqyjoNd1Vpcti5iK67UENJ0k14KXLdvG1Pd\nNt2kbj" + "rVRaR9w8SFzaVTvn7kIMNve06R64Jh/y8cFYyt7i62mLPdtKppFUE9/8cWe/" + "XKpoZljUuX\n3U9QSc+m9u4wz5eU8KPp7LkfbjaZuqIS2lkYVbJon+aov021" +
           "Eu5NkCGvq61lA+/U6NhIh7XcPiuy\nFVjmRvUe+4HR7s51dWTX3kM7/lZ6UH" + "hrF88+ld7rGOXpT3sv43sf/PLvs9wUCuyrv0MwROl5Gv4O\ndqV2KufTL337" + "InlvyWpOb5Lq4AdcvPr0ePXqV4/cQZtf7dOAH3XF6NyHQ8Py28zH7EKc8Vrg" + "BWr2\nBTTwYxlqj6cIL0gX4Spqi0a7EKcKsqcIO1ZeyMZ7eOscBCXr1qAiQw" + "tbau4FNzQ0FZpRKelrPgN2\nI5UzrOnG/kk61ifoMAY+p1vmMFhxtvs5zZAT" + "EGyj7OJx82DtT97uPXUoW5X2vpm5oWLiFz783Ujo\n2TcHOZy/N/MdPjr/zB" + "9fu9k1gzsdf79ZmPGE4obhbzhMlDKdun3NZBTY6beW1Ly8v+sG44jCaSD9\n" + "qCZLTrAlJwk2b5jRP2ba3kV0kU7W2PZec5v2DtD5Pr9x3XZ6fpK9Y3R4lvD7" + "CjvxpN2WPEVQSLaf\nHZlkz92hZMyTa+HbakvWeruezCSjw8Rk4n1zkr1v0e" + "EkSKJrueuXI+Kp2xURilxR+oUjhbgi4xWE\nBkXGWyl/3xOj1/cNfBr9zT/Z" + "JT79BlcCdSpuKYq7T3fNC3QDx2UmWgnv2nX2cwEsSKnT+XezScqr\nubdQ04" + "vswpwNXofFX5Fj4o5Xdi5IHul5jrdl0DuMj9tNUCF/QUhnzZqc2FK43kUXXu" + "17/TsPpALI\n87aQ4USNfHcSo0Cmzn7Jb0vohF3Lx3846/sPnj15I8heF/4L" + "+LScIvoXAAA=");
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
                ecor.CalibratorStack.push(new ecor.Calibrator() {
                    double demandRatio = (double)(todo)/(todo +
                      finished);
                    double supplyRatio = 0;
                    public int mode = $UTILMODES.$MAX;
                    public int getMode(int max) {
                            return mode;
                    }
                    public int supply() {
                    return ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity();}
                        private double supplyLimit = 12345;
                    private int initialSupply = this.supply();
                    public Object calibrate(Object input) {
                            supplyRatio = (supplyLimit - (initialSupply - this.supply()))/supplyLimit;
                            return input;
                            }
                    public void adjust() {
                            if (supplyRatio > demandRatio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                                else if (demandRatio > supplyRatio * 1.1 && mode > 0) --mode;
                                }
                });
                {
                    while (true) {
                        ecor.CalibratorStack.push(new ecor.Calibrator() {
                            private ecor.Calibrator original = ecor.CalibratorStack.peek();
                                    public int getMode(int max) {
                                    return original.getMode($UTILMODES.$MAX);
                            }
                            public Object calibrate(Object input) {
                                    return input;
                            }
                        public int supply() {
                        return 0;}
                            public void adjust() {}
                                    });
                        {
                            String fileName = _queue.pop();
                            if (fileName.equals("")) break;
                            Transformer transformer = null;
                            transformer.setErrorListener(this);
                            FileInputStream inputStream = new FileInputStream(new File(scratch, fileName));
                            Source inFile = new StreamSource(inputStream);
                            transformer.transform(inFile, outFile);
                            inputStream.close();
                        }
                        ecor.CalibratorStack.pop();
                        finished += 1;
                        todo = _workQueue.size() * 1;
                    }
                }
                ecor.CalibratorStack.pop();
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
        
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1417475253000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAL1Ye2wUxxmfe9jGZ1d+AIbwMjbOC9BtX6QUEwXiHonxEhy/" + "cOyEY7w7d168t7vM\nztqHg0hQ1UCThgSRSlRqAFVIkDRpIqUVrZSmREnaNK" + "hSUqmpFCm0FVJbqU2lqlJK1f7Rb2Z3b2/3\n7hyUoFja8dzOfN833+v3fbMv" + "fITqbIpWKXaaHbSIne4bHsTUJmqfjm17BF5llbfqGgfPDxhmHMVk\nFNdUhl" + "pkxZZUzLCkqVL/N3qLFG2wTP1gXjdZmhRZer++yeO3U95UwXDP6YvtR84lO+" + "OoTkYt2DBM\nhplmGhmdFGyGWuX9eBZLDtN0SdZs1iujLxDDKfSZhs2wwewD" + "6DBKyKjeUjhPhrpkX7gEwiULU1yQ\nhHhpUIgFDospYVgziLq9JA4oN4Yp4d" + "ge3VDlbmCyiC+OgTriBKD12pLWrrYVqlqJC2N3HDr7XAK1\nTKAWzRjmzBTQ" + "hIG8CdRcIIUpQu3tqkrUCdRmEKIOE6phXZsXUidQu63lDcwcSuwhYpv6LN/Y" + "bjsW\noUKm/1JGzQrXiToKM2nJRjmN6Kr/qy6n4zyo3RGo7aq7g78HBVMaHI" + "zmsEJ8kuSMZoDHO6MUJR17\nBmADkDYUCJs2S6KSBoYXqN31pY6NvDTMqGbk" + "YWud6YAUhlbUZMptbWFlBudJlqHl0X2D7hLsahSG\n4CQMLY1uE5zASysiXi" + "rzz4aOj49d+P5r20RsJ1Wi6Pz8KSBaEyEaIjlCiaEQl/Cak36m/wFnVRwh\n" + "2Lw0stnds/3mi6PyX3/R6e5ZWWXP7qn9RGFZ5b4TnUMP32OiBD/GIsu0Ne78" + "kOYiHQa9ld6iBVnb\nUeLIF9P+4qWhXz7w6PPkb3GU6kf1iqk7BYijNsUsWJ" +
           "pO6D3EIBQzovajRmKofWK9HzXAXIaQd9/u\nzuVswvpRUhev6k3xG0yUAxbc" + "RI0w14yc6c8tzKbFvGghhGLwoC3wjCP3T/xnaJc0akO4S1jBhmaY\nUl6DxF" + "NMKS2pJIcdnWWLGGJFsqkimTQPGKNgy5Tcl+PD8sjd4IHpjGKmeVhZDA3UYP" + "hp2BX5+dvn\nYjEOiNHE1iEn7jV1ldCscv7qO4cyA98+5gYND3RPc4ZWM2Kz" + "dDnrnnEubY9JZwhFsZjgvoSLc50G\nJp+B5AWYa759+KGd+451JyBarLkkty" + "Js7QZtvDMAs74gw/sFGCoQZst/MHk0fe38XW6YSbWBuCp1\n07svXj77r+b1" + "cRSvjpJcN8DpFGczyKG1hH490byqxv8fj+965f3LH94WZBhDPRWJX0nJE7c7" + "6gVq\nKkQFKAzYn7upJbEHjZ2IoySgASCgOD+Ay5qojFAC9/pgyHVpkFFTzq" + "QFrPMlH8FSbJqac8EbER6t\nYr4YnNPII3oJPPu8EBf/+epSi48dbjhxb0e0" + "EGB77Zv1X/z9q01vCbP4uNxSVvmGCXOzvC0IlhFK\nCLz/8NTgye9+dHRSRI" + "oXKgzKoTOla0oRSG4JSCC9dYAY7sieUaNgqlpOw1M64RH3v5abv/STvx9v\n" + "dV2jwxvfsxs/mUHw/qa70aOX9/57jWATU3h5CdQItrnaLA44b6cUH+TnKB75" + "7erv/Qo/C+gHiGNr\n80SASFxoFhcWX8bQysrU4ll1v0McAoyXl7cwVCsAFM" + "4KV199rPvnb4+eOeqmx+0L9CnlVFnlkT/8\ncSbx1OtTLl20HEQ2n1hz7s+v" + "XB1a4prSrZnrKspWOY1bN0WQtFjcaV0LSRC739zQ9cLhoSveidrD\n6J+BDu" +
           "kvB98gt2598k9VgCkBlV0Ik4Q914sxzUNWWBmJta186IazbKxhpSptUVY59H" + "y+2znw658J\nqU24vL8qD+hd2HLVbeXDOq7ysigO3ovtadj31Uv3PdiqX/ov" + "cJwAjgq0I/ZuCthbDKWDt7uu4YPX\n3+jY914CxXeglG5idQcWSIIaIYWJPQ" + "2wXbTu2iaytHVuER+FykgYYYVngGLZr7i9YKTs4E1VgEHZ\nqY0X5Hd2PysM" + "UBNCqwRRhM/8a6Onr/2GXRF8Aizj1F3FypoEjWhAu/n92bb6l88U4qhhArUq" + "Xqs8\nhnWHI8YEdHa23z9DOx1aD3dpbkvSW8LqVdEoLhMbRdEg5GDOd/N5cw" + "Q449zafDLpAedkFDhjSEx2\nCpIeMd5qeV4CnMseECl/HbDgIjAf7+TDgOvi" + "bTVDIRM+ZAqevd4h99Y45DAfZMivrKby6R0RmSML\nyBSGkcvCLuYr1SX6kX" + "SxoKcZxYbNq1M6Q6lJOV7yBo6nz+pafa1Ai6Pj/2x+DL/5UNxL7s1gOu+6\n" + "EUhMcjahFmOXaOODwHr8uR9eZO9t2OKCzvraSRElXL/l7Hznlpee+BSNRWdE" + "sSjrttmV9yemtbfj\n4qbhxmnFDSVM1BuOzhScx6HGSChG14aLO58Qz/2kan" + "Hnwy0L4GlugTXRLUOxTFDHNcWXLTdUvsZQ\nctbU1CCG1E+KWx/CxI9sWIuv" + "wHPc0+L4dWsRC8fjbdXiccSfEZopKsTiThUc7RvJbI4PUMDqCA9/\n/mN/YJ" + "kDn8Uym+E56Vnm5OdjmSM3ktm3+HCYoVQOM6xnqpjnkc9ink3wnPLMc+rzMc" + "9TN5LZST58\nh6GGOUzhbpiP2ObJ67UNlJymsjucf5i2irrDm9CK70HuNwxF" +
           "/uDhBz+Wf/cfcU0pfWdogst+ztH1\n8rJZNq+3KMlpQpkmt4ha4t9pQAgunc" + "/PWP6BWkVjxGt4egTaHqwWQ2rw5ntdCL3FdzIfYR33S1lW\nGX9xcm3xiZGn" + "BWzXKTqen+dsUjJqcO9IJZTuqsnN5/UuevmlsVd/9HW/BInOb0kxKJGh+LvT" + "XV3A\nLVAZql9MMgWLiavE/E+X/Xjr+dNX4uJq9H+fZLoK3hQAAA==");
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
        _template = (new Templates[] { _templateLo, _templateHi, _templateFull, });
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
        long start = System.currentTimeMillis();
        bench.doWork(13000);
        long end = System.currentTimeMillis() - start;
        System.out.println("Work finished: " + end + " in total.");
    }
    
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1417475253000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1ZC2wUxxken23OL+IHjwABGxzzNncFihtw1MTBJjl82I5t" + "DDHQy3hv7rywt7vs\nzpnDQWmipIEG5YGaSK0UCKGkEBIapLSilVIKIqSUKG" + "qCmkShgrRCSqqkiRJVovQhtf8/u3e7t3fn\nAI2lHe9j5p/vf33zz9wrn5NS" + "0yDTJTPAt+vMDKzs66GGyaIrFWqa/fAqIp0pLe851KlqPlIUJj45\nykl1WD" + "KDUcppUI4GQ+2tKYMs1DVle1zReICleGCzssyWtzq8LEfgun3H6x4+WNLgI6" + "VhUk1VVeOU\ny5raobCEyUlNeDMdocEkl5VgWDZ5a5iMZ2oysVJTTU5Vbm4l" + "D5LiMBmnSyiTk1nh9ORBmDyoU4Mm\ngmL6YI+YFiRMMBinssqibZnpYGRz9k" + "iAbY/rze0NQsrw4wCoIxCA1jMzWlva5qiqFx8eaNmx/6Vi\nUj1IqmW1D4VJ" + "oAmH+QZJVYIlhphhtkWjLDpIalXGon3MkKkij4pZB0mdKcdVypMGM3uZqSkj" + "2LHO\nTOrMEHOmX4ZJlYQ6GUmJa0bGRjGZKdH0U2lMoXFQe7KjtqXuKnwPCl" + "bIAMyIUYmlh5RskVXweIN3\nREbHpk7oAEP9CcaHtcxUJSqFF6TO8qVC1Xiw" + "jxuyGoeupVoSZuFkWkGhaGudSltonEU4meLt12N9\ngl7lwhA4hJNJ3m5CEn" + "hpmsdLLv8snHxl1+HnTtwpYrskyiQF8VfAoHrPoF4WYwZTJWYNvJoMPBO6\n" + "LzndRwh0nuTpbPVpm318bfivv22w+tySp0/30GYm8YjUtaeh94G7NVKMMMp0" + "zZTR+Vmai3Tosb+0\npnTI2skZifgxkP54svfN+x46wj7zkYoQGSdpSjIBcV" +
       "QraQldVphxN1OZQTmLhkg5U6MrxfcQ8cN9\nGELeetsdi5mMh0iJIl6N08Qz" + "mCgGItBE5XAvqzEtfa9TPizuUzohxA8XqYKrkVh/4j8na4JrTQj3\nIJWoKq" + "taMC5D4klaMBCMshhNKjySohArQdOQgpoRB46RqK4FrZfr+8L9d4EHhjskLY" + "BhpXPSWUDg\njYhLIf66bUVFSIjexFYgJ+7RlCgzItKhy+d2dHT+cJcVNBjo" + "tuac1HJm8oBbNCkqEhIn4hSWo8DM\nWyBhgdqq5vdtWn3/rsZiiBB9WwnYCL" + "s2ggb2vCBgpZPVIUGAEoTWlAMbdgauHrrDCq1gYfLNO7ry\nnaNv7f971QIf" + "8eVnRtQHuLkCxfQgnWYYr8mbS/nkf/H4mtfef+viPCerOGnKSfbckZisjV7L" + "G5rE\nokB/jviDU6uL15GBPT5SAgwArCfwA6HUe+fIStrWNAGiLv4wqYxpRo" + "Iq+CnNWhV82NC2OW9ESNSI\n+wngnDKM4olwHbLDWvzHr5N0bCdbIYTe9mgh" + "CPbqI+O+9cHrlWeEWdJcXO1a7foYtzK71gmWfoMx\neH/xxz0/evbznRtEpN" + "ihwmEJTA4pspSCIXOcIZDSCtAKOrJprZrQonJMpkMKw4j7T/Xsxb/825M1\n" + "lmsUeJP2bPPXC3DeT72LPPTW9/5RL8QUSbikOGo43SxtJjiS2wyDbkccqYfP" + "z/jJ7+heYDxgGVMe\nZYI4iuwkQFA3c1IlRspaYBXQjjBuUHxaINpA7gBrKl" + "xuAh0piekIQoxbik0jgGkukCt5VvyItONI\nvDG59fe/FmpWUnfp4PbbGqq3" + "WqGCza3ojpu96X4PNYeh37dPdm2sUU7+GyQOgkQJVlqz2wBaSWV5\n3e5d6r" +
       "9w6vTk+98tJr5VpELRaHQVFQlDyiFSmTkMjJTS77hTBGPNNozPGrxNEWGQac" + "I4JJVyPVUC\nuPmF+WIV1gtOqkWGmg+Hz3XvFQYoyBR5lkuPnNETa/ddfZtf" + "EnKclMXRs1K5dAs1ljP2tvdHascd\nez7hI/5BUiPZVeAAVZKYGINQtJjp0h" + "Aqxazv2QWItdq2ZihpupcuXNN6ycKhebjH3nhf5eEHsew1\nwNVk80OTlx/E" + "UlDrBOldmqYwqr4903jovef+9YWIitIRhA7pgENWCuFNop2byfvSmKxSJcWJ" + "f4QZ\nQ5rJQOoUdw1vyAmoBUYE711+rPE3Z9c+v9NaK8bwfdaoiPT9j/68pf" + "ipU0PWOK+DPZ331B/8+LXL\nvRMtXrGKxltz6jb3GKtwFBas1jFlZo01g+j9" + "xsJZrzzYe8lGVJdd/nTAFuGT7afZ3Nuf+Eueldk/\nZJnaYmhsW7Bpt3Jjec" + "Ec+m7Gu+X4dipcc2zvzsnxLhE3A3l95tcNeYRi3Q8BpEU18blTt+bvAS6P\n" + "akDmzINv3XXimwHXXBvf3AL4KDbrOSmDKJLNYRbF542eiYeufeLq9MTz7Inn" + "FZg4lmsYH97PB+1N\nsTMC45SCo1nKa51i2Jl4EMavE+FquObbCOcXQLjVNo" + "1/axL2YHw7hOUkpxrroRw2LirG48ttmy4t\neab+tBVpQ9R0EYILdsmIJgvz" + "Kh7wxnWCXw5Xsw2+OZdVYNe8HqvamauBWmYuCXwnsBg/jRYweAib\nQTD2+P" + "Vt4bauyEBHb1+ouyu9itY4BGVt2jzgH7hO8NPhWmSDX1TA8o9+HVS/KcG2RR" + "rOWxt4AP7g\n2gEW4dvxcAVtgMECAJ/IBUgQ1jbN2MKsDbfXyU9eOwx/2k77" +
       "bRj7C8B4BpunOCmPcJbQFSAUIYPr\nIvRG0taZIXY0gVRCCXCDqibWuoF+9w" + "gXzmev01yY6wdsnAcK4HzOxlkRQQPdm2RJlsZ2S84mqWld\nuo8H2d7rRIaL" + "74s2shcLIPupjawsYrsO63XX4ilqVMzxl55un9C7fMMjYptUDnxAzS6nCvBZ" + "iV0E\nK9HswmtqRlhEmrvp+JenTrC5YpEvk02oNtqMeJ7zCdeYr+gRtuaD2D" + "6x1XFoxnuwk3tuk3UcI0Lj\npmxLVY1lqUwU5XpK0Mw6YThd12FP7xeGWbwE" + "rFgHVsRjwYActQvA9vOrho7E1CNRn4ApQLdhf9uI\n5eKNy6rFmm7iuYfrgN" + "GW1NStm7hBHO+aJNS+49jqqrIXdj8m5NsuKXedodjP/hFqdLnLgQoLdUvL\n" + "0ts46f8GTxFWLGlZ1rx00eLFQK7994T6AhEXQRx1Rd2DBpmcay/U0vYKqRNl" + "zk2uLQ2UOO6PoEdJ\nb0dbuydpDo6RNCIU5mTtDsBxMwqdkImya+f6r6oeo2" + "9swrILB94L7MM1fZHCRpjiElWCkrLOLtaI\nIHRK+cdfevk4f3fhCquAW1A4" + "bbwDF6zYP9qw4tXdN3Bi0eDRzSu6duSWe4uH5bMiguydQc5xZ/ag\n1uz9QA" + "XgSVplgVMEzMyty7pt13V70034T1Qent0tsV2Ez2fH+HYOmzOcVMYZ7zG0OG" + "wNRb+NTlC8\n+XVMmgkHfDiVfeaBleSHNvoPC6Kfk39vLlZG0ev8GCr8EZs/" + "QNJIBoMlap1Nzfi20722CWXe+X+U\nqYfrU1uZT29cmYtjn0TUO2kbQnI2kj" + "pn0exDiY+wuSBKf9QXn953lPzTDSpZbXusaLylo/U/R8lC\ntTg2m1NjKPez" +
       "zEy4GcIlt/BMBStKa+0os1h46TLP4hHWJKqE2l84VXl+T7JldZp4jmHzJSdr" + "v1m+\nXt68ZNkiXAdKqBE3xUyf3eAx0xVsPgZJCSAojz8/uVZ/QlVZ5caJZ2" + "hTcn5psn4dkcIXHth4Jfze\nP60KIf0LRiUsgbGkorhPLVz343SDxWSBuNI6" + "w7BS7L+4N2XiTBCKpTzOs45QUllwEd6tWVQufoFL\n023S+g0uIq0/umFman" + "f/04LDSyWFjo6imApYna2T2AxlzyooLS3rHXLs1YHXf748HRni4G2iK7Cz\n" + "cqPF+jqG+WGZyH/82ZHQuTiwHP3Vzb+4/dC+Sz5xAPs/GoV+NDgdAAA=");
}
