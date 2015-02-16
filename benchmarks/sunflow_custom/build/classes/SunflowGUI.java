import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.sunflow.Benchmark;
import org.sunflow.RealtimeBenchmark;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Display;
import org.sunflow.core.Shader;
import org.sunflow.core.TextureCache;
import org.sunflow.core.accel.KDTree;
import org.sunflow.core.display.FileDisplay;
import org.sunflow.core.display.FrameDisplay;
import org.sunflow.core.display.ImgPipeDisplay;
import org.sunflow.core.display.OpenExrDisplay;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.shader.AmbientOcclusionShader;
import org.sunflow.core.shader.IDShader;
import org.sunflow.core.shader.NormalShader;
import org.sunflow.core.shader.PrimIDShader;
import org.sunflow.core.shader.QuickGrayShader;
import org.sunflow.core.shader.UVShader;
import org.sunflow.core.shader.WireframeShader;
import org.sunflow.image.Color;
import org.sunflow.system.ImagePanel;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UserInterface;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
@SuppressWarnings("serial") 
public class SunflowGUI extends javax.swing.JFrame implements UserInterface {
    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 768;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JMenuItem exitMenuItem;
    private JSeparator jSeparator2;
    private JPanel jPanel1;
    private JButton renderButton;
    private JMenuItem jMenuItem4;
    private JSeparator jSeparator1;
    private JMenuItem fitWindowMenuItem;
    private JMenuItem tileWindowMenuItem;
    private JSeparator jSeparator5;
    private JMenuItem consoleWindowMenuItem;
    private JMenuItem editorWindowMenuItem;
    private JMenuItem imageWindowMenuItem;
    private JMenu windowMenu;
    private JInternalFrame consoleFrame;
    private JInternalFrame editorFrame;
    private JInternalFrame imagePanelFrame;
    private JDesktopPane desktop;
    private JCheckBoxMenuItem smallTrianglesMenuItem;
    private JMenuItem textureCacheClearMenuItem;
    private JSeparator jSeparator4;
    private JMenuItem resetZoomMenuItem;
    private JMenu imageMenu;
    private ImagePanel imagePanel;
    private JPanel jPanel6;
    private JCheckBoxMenuItem clearLogMenuItem;
    private JPanel jPanel5;
    private JButton taskCancelButton;
    private JProgressBar taskProgressBar;
    private JSeparator jSeparator3;
    private JCheckBoxMenuItem autoBuildMenuItem;
    private JMenuItem iprMenuItem;
    private JMenuItem renderMenuItem;
    private JMenuItem buildMenuItem;
    private JMenu sceneMenu;
    private JTextArea editorTextArea;
    private JTextArea consoleTextArea;
    private JButton clearConsoleButton;
    private JPanel jPanel4;
    private JScrollPane jScrollPane2;
    private JButton iprButton;
    private JButton buildButton;
    private JMenuItem saveAsMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem openFileMenuItem;
    private JMenuItem newFileMenuItem;
    private JMenu fileMenu;
    private JMenuBar jMenuBar1;
    private String currentFile;
    private String currentTask;
    private int currentTaskLastP;
    private SunflowAPI api;
    public static void usage(boolean verbose) { System.out.println("Usage: SunflowGUI [options] scenefile");
                                                if (verbose) { System.out.
                                                                 println(
                                                                   "Sunflow v" +
                                                                   SunflowAPI.
                                                                     VERSION +
                                                                   " textmode");
                                                               System.out.
                                                                 println(
                                                                   "Renders the specified scene file");
                                                               System.out.
                                                                 println(
                                                                   "Options:");
                                                               System.out.
                                                                 println(
                                                                   ("  -o filename      Saves the output as the specified filenam" +
                                                                    "e (png, hdr, tga)"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("                   #\'s get expanded to the current frame nu" +
                                                                    "mber"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -nogui           Don\'t open the frame showing rendering p" +
                                                                    "rogress"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -ipr             Render using progressive algorithm");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -sampler type    Render using the specified algorithm");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -threads n       Render using n threads");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -lopri           Set thread priority to low (default)");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -hipri           Set thread priority to high");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -smallmesh       Load triangle meshes using triangles opti" +
                                                                    "mized for memory use"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -dumpkd          Dump KDTree to an obj file for visualizat" +
                                                                    "ion"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -buildonly       Do not call render method after loading t" +
                                                                    "he scene"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -showaa          Display sampling levels per pixel for buc" +
                                                                    "ket renderer"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -nogi            Disable any global illumination engines i" +
                                                                    "n the scene"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -nocaustics      Disable any caustic engine in the scene");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -pathgi n        Use path tracing with n samples to render" +
                                                                    " global illumination"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -quick_ambocc d  Applies ambient occlusion to the scene wi" +
                                                                    "th specified maximum distance"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -quick_uvs       Applies a surface uv visualization shader" +
                                                                    " to the scene"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -quick_normals   Applies a surface normal visualization sh" +
                                                                    "ader to the scene"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -quick_id        Renders using a unique color for each ins" +
                                                                    "tance"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -quick_prims     Renders using a unique color for each pri" +
                                                                    "mitive"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -quick_gray      Renders using a plain gray diffuse shader");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -quick_wire      Renders using a wireframe shader");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -resolution w h  Changes the render resolution to the spec" +
                                                                    "ified width and height (in pixels)"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -aa min max      Overrides the image anti-aliasing depths");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -bucket n order  Changes the default bucket size to n pixe" +
                                                                    "ls and the default order"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -bake name       Bakes a lightmap for the specified instan" +
                                                                    "ce"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -bakedir dir     Selects the type of lightmap baking: dir=" +
                                                                    "view or ortho"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -filter type     Selects the image filter to use");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -bench           Run several built-in scenes for benchmark" +
                                                                    " purposes"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -rtbench         Run realtime ray-tracing benchmark");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -frame n         Set frame number to the specified value");
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -anim n1 n2      Render all frames between the two specifi" +
                                                                    "ed values (inclusive)"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   ("  -v verbosity     Set the verbosity level: 0=none,1=errors," +
                                                                    "2=warnings,3=info,4=detailed"));
                                                               System.
                                                                 out.
                                                                 println(
                                                                   "  -h               Prints this message");
                                                }
                                                System.
                                                  exit(
                                                    1);
    }
    public static void main(String[] args) {
        if (args.
              length >
              0) {
            boolean showFrame =
              true;
            String sampler =
              "bucket";
            boolean noRender =
              false;
            String filename =
              null;
            String input =
              null;
            int i =
              0;
            int threads =
              0;
            boolean lowPriority =
              true;
            boolean showAA =
              false;
            boolean noGI =
              false;
            boolean noCaustics =
              false;
            int pathGI =
              0;
            Shader shaderOverride =
              null;
            int resolutionW =
              0;
            int resolutionH =
              0;
            int aaMin =
              -5;
            int aaMax =
              -5;
            int bucketSize =
              0;
            String bucketOrder =
              null;
            String bakingName =
              null;
            boolean bakeViewdep =
              false;
            String filterType =
              null;
            boolean runBenchmark =
              false;
            boolean runRTBenchmark =
              false;
            int frameStart =
              1;
            int frameStop =
              1;
            while (i <
                     args.
                       length) {
                if (args[i].
                      equals(
                        "-o")) {
                    if (i >
                          args.
                            length -
                          2)
                        usage(
                          false);
                    filename =
                      args[i +
                             1];
                    i +=
                      2;
                }
                else
                    if (args[i].
                          equals(
                            "-nogui")) {
                        showFrame =
                          false;
                        i++;
                    }
                    else
                        if (args[i].
                              equals(
                                "-ipr")) {
                            sampler =
                              "ipr";
                            i++;
                        }
                        else
                            if (args[i].
                                  equals(
                                    "-threads")) {
                                if (i >
                                      args.
                                        length -
                                      2)
                                    usage(
                                      false);
                                threads =
                                  Integer.
                                    parseInt(
                                      args[i +
                                             1]);
                                i +=
                                  2;
                            }
                            else
                                if (args[i].
                                      equals(
                                        "-lopri")) {
                                    lowPriority =
                                      true;
                                    i++;
                                }
                                else
                                    if (args[i].
                                          equals(
                                            "-hipri")) {
                                        lowPriority =
                                          false;
                                        i++;
                                    }
                                    else
                                        if (args[i].
                                              equals(
                                                "-sampler")) {
                                            if (i >
                                                  args.
                                                    length -
                                                  2)
                                                usage(
                                                  false);
                                            sampler =
                                              args[i +
                                                     1];
                                            i +=
                                              2;
                                        }
                                        else
                                            if (args[i].
                                                  equals(
                                                    "-smallmesh")) {
                                                TriangleMesh.
                                                  setSmallTriangles(
                                                    true);
                                                i++;
                                            }
                                            else
                                                if (args[i].
                                                      equals(
                                                        "-dumpkd")) {
                                                    KDTree.
                                                      setDumpMode(
                                                        true,
                                                        "kdtree");
                                                    i++;
                                                }
                                                else
                                                    if (args[i].
                                                          equals(
                                                            "-buildonly")) {
                                                        noRender =
                                                          true;
                                                        i++;
                                                    }
                                                    else
                                                        if (args[i].
                                                              equals(
                                                                "-showaa")) {
                                                            showAA =
                                                              true;
                                                            i++;
                                                        }
                                                        else
                                                            if (args[i].
                                                                  equals(
                                                                    "-nogi")) {
                                                                noGI =
                                                                  true;
                                                                i++;
                                                            }
                                                            else
                                                                if (args[i].
                                                                      equals(
                                                                        "-nocaustics")) {
                                                                    noCaustics =
                                                                      true;
                                                                    i++;
                                                                }
                                                                else
                                                                    if (args[i].
                                                                          equals(
                                                                            "-pathgi")) {
                                                                        if (i >
                                                                              args.
                                                                                length -
                                                                              2)
                                                                            usage(
                                                                              false);
                                                                        pathGI =
                                                                          Integer.
                                                                            parseInt(
                                                                              args[i +
                                                                                     1]);
                                                                        i +=
                                                                          2;
                                                                    }
                                                                    else
                                                                        if (args[i].
                                                                              equals(
                                                                                "-quick_ambocc")) {
                                                                            if (i >
                                                                                  args.
                                                                                    length -
                                                                                  2)
                                                                                usage(
                                                                                  false);
                                                                            float d =
                                                                              Float.
                                                                              parseFloat(
                                                                                args[i +
                                                                                       1]);
                                                                            shaderOverride =
                                                                              new AmbientOcclusionShader(
                                                                                Color.
                                                                                  WHITE,
                                                                                d);
                                                                            i +=
                                                                              2;
                                                                        }
                                                                        else
                                                                            if (args[i].
                                                                                  equals(
                                                                                    "-quick_uvs")) {
                                                                                if (i >
                                                                                      args.
                                                                                        length -
                                                                                      1)
                                                                                    usage(
                                                                                      false);
                                                                                shaderOverride =
                                                                                  new UVShader(
                                                                                    );
                                                                                i++;
                                                                            }
                                                                            else
                                                                                if (args[i].
                                                                                      equals(
                                                                                        "-quick_normals")) {
                                                                                    if (i >
                                                                                          args.
                                                                                            length -
                                                                                          1)
                                                                                        usage(
                                                                                          false);
                                                                                    shaderOverride =
                                                                                      new NormalShader(
                                                                                        );
                                                                                    i++;
                                                                                }
                                                                                else
                                                                                    if (args[i].
                                                                                          equals(
                                                                                            "-quick_id")) {
                                                                                        if (i >
                                                                                              args.
                                                                                                length -
                                                                                              1)
                                                                                            usage(
                                                                                              false);
                                                                                        shaderOverride =
                                                                                          new IDShader(
                                                                                            );
                                                                                        i++;
                                                                                    }
                                                                                    else
                                                                                        if (args[i].
                                                                                              equals(
                                                                                                "-quick_prims")) {
                                                                                            if (i >
                                                                                                  args.
                                                                                                    length -
                                                                                                  1)
                                                                                                usage(
                                                                                                  false);
                                                                                            shaderOverride =
                                                                                              new PrimIDShader(
                                                                                                );
                                                                                            i++;
                                                                                        }
                                                                                        else
                                                                                            if (args[i].
                                                                                                  equals(
                                                                                                    "-quick_gray")) {
                                                                                                if (i >
                                                                                                      args.
                                                                                                        length -
                                                                                                      1)
                                                                                                    usage(
                                                                                                      false);
                                                                                                shaderOverride =
                                                                                                  new QuickGrayShader(
                                                                                                    );
                                                                                                i++;
                                                                                            }
                                                                                            else
                                                                                                if (args[i].
                                                                                                      equals(
                                                                                                        "-quick_wire")) {
                                                                                                    if (i >
                                                                                                          args.
                                                                                                            length -
                                                                                                          1)
                                                                                                        usage(
                                                                                                          false);
                                                                                                    shaderOverride =
                                                                                                      new WireframeShader(
                                                                                                        );
                                                                                                    i++;
                                                                                                }
                                                                                                else
                                                                                                    if (args[i].
                                                                                                          equals(
                                                                                                            "-resolution")) {
                                                                                                        if (i >
                                                                                                              args.
                                                                                                                length -
                                                                                                              3)
                                                                                                            usage(
                                                                                                              false);
                                                                                                        resolutionW =
                                                                                                          Integer.
                                                                                                            parseInt(
                                                                                                              args[i +
                                                                                                                     1]);
                                                                                                        resolutionH =
                                                                                                          Integer.
                                                                                                            parseInt(
                                                                                                              args[i +
                                                                                                                     2]);
                                                                                                        i +=
                                                                                                          3;
                                                                                                    }
                                                                                                    else
                                                                                                        if (args[i].
                                                                                                              equals(
                                                                                                                "-aa")) {
                                                                                                            if (i >
                                                                                                                  args.
                                                                                                                    length -
                                                                                                                  3)
                                                                                                                usage(
                                                                                                                  false);
                                                                                                            aaMin =
                                                                                                              Integer.
                                                                                                                parseInt(
                                                                                                                  args[i +
                                                                                                                         1]);
                                                                                                            aaMax =
                                                                                                              Integer.
                                                                                                                parseInt(
                                                                                                                  args[i +
                                                                                                                         2]);
                                                                                                            i +=
                                                                                                              3;
                                                                                                        }
                                                                                                        else
                                                                                                            if (args[i].
                                                                                                                  equals(
                                                                                                                    "-bucket")) {
                                                                                                                if (i >
                                                                                                                      args.
                                                                                                                        length -
                                                                                                                      3)
                                                                                                                    usage(
                                                                                                                      false);
                                                                                                                bucketSize =
                                                                                                                  Integer.
                                                                                                                    parseInt(
                                                                                                                      args[i +
                                                                                                                             1]);
                                                                                                                bucketOrder =
                                                                                                                  args[i +
                                                                                                                         2];
                                                                                                                i +=
                                                                                                                  3;
                                                                                                            }
                                                                                                            else
                                                                                                                if (args[i].
                                                                                                                      equals(
                                                                                                                        "-bake")) {
                                                                                                                    if (i >
                                                                                                                          args.
                                                                                                                            length -
                                                                                                                          2)
                                                                                                                        usage(
                                                                                                                          false);
                                                                                                                    bakingName =
                                                                                                                      args[i +
                                                                                                                             1];
                                                                                                                    i +=
                                                                                                                      2;
                                                                                                                }
                                                                                                                else
                                                                                                                    if (args[i].
                                                                                                                          equals(
                                                                                                                            "-bakedir")) {
                                                                                                                        if (i >
                                                                                                                              args.
                                                                                                                                length -
                                                                                                                              2)
                                                                                                                            usage(
                                                                                                                              false);
                                                                                                                        String baketype =
                                                                                                                          args[i +
                                                                                                                                 1];
                                                                                                                        if (baketype.
                                                                                                                              equals(
                                                                                                                                "view"))
                                                                                                                            bakeViewdep =
                                                                                                                              true;
                                                                                                                        else
                                                                                                                            if (baketype.
                                                                                                                                  equals(
                                                                                                                                    "ortho"))
                                                                                                                                bakeViewdep =
                                                                                                                                  false;
                                                                                                                            else
                                                                                                                                usage(
                                                                                                                                  false);
                                                                                                                        i +=
                                                                                                                          2;
                                                                                                                    }
                                                                                                                    else
                                                                                                                        if (args[i].
                                                                                                                              equals(
                                                                                                                                "-filter")) {
                                                                                                                            if (i >
                                                                                                                                  args.
                                                                                                                                    length -
                                                                                                                                  2)
                                                                                                                                usage(
                                                                                                                                  false);
                                                                                                                            filterType =
                                                                                                                              args[i +
                                                                                                                                     1];
                                                                                                                            i +=
                                                                                                                              2;
                                                                                                                        }
                                                                                                                        else
                                                                                                                            if (args[i].
                                                                                                                                  equals(
                                                                                                                                    "-bench")) {
                                                                                                                                runBenchmark =
                                                                                                                                  true;
                                                                                                                                i++;
                                                                                                                            }
                                                                                                                            else
                                                                                                                                if (args[i].
                                                                                                                                      equals(
                                                                                                                                        "-rtbench")) {
                                                                                                                                    runRTBenchmark =
                                                                                                                                      true;
                                                                                                                                    i++;
                                                                                                                                }
                                                                                                                                else
                                                                                                                                    if (args[i].
                                                                                                                                          equals(
                                                                                                                                            "-frame")) {
                                                                                                                                        if (i >
                                                                                                                                              args.
                                                                                                                                                length -
                                                                                                                                              2)
                                                                                                                                            usage(
                                                                                                                                              false);
                                                                                                                                        frameStart =
                                                                                                                                          (frameStop =
                                                                                                                                             Integer.
                                                                                                                                               parseInt(
                                                                                                                                                 args[i +
                                                                                                                                                        1]));
                                                                                                                                        i +=
                                                                                                                                          2;
                                                                                                                                    }
                                                                                                                                    else
                                                                                                                                        if (args[i].
                                                                                                                                              equals(
                                                                                                                                                "-anim")) {
                                                                                                                                            if (i >
                                                                                                                                                  args.
                                                                                                                                                    length -
                                                                                                                                                  3)
                                                                                                                                                usage(
                                                                                                                                                  false);
                                                                                                                                            frameStart =
                                                                                                                                              Integer.
                                                                                                                                                parseInt(
                                                                                                                                                  args[i +
                                                                                                                                                         1]);
                                                                                                                                            frameStop =
                                                                                                                                              Integer.
                                                                                                                                                parseInt(
                                                                                                                                                  args[i +
                                                                                                                                                         2]);
                                                                                                                                            i +=
                                                                                                                                              3;
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                            if (args[i].
                                                                                                                                                  equals(
                                                                                                                                                    "-v")) {
                                                                                                                                                if (i >
                                                                                                                                                      args.
                                                                                                                                                        length -
                                                                                                                                                      2)
                                                                                                                                                    usage(
                                                                                                                                                      false);
                                                                                                                                                UI.
                                                                                                                                                  verbosity(
                                                                                                                                                    Integer.
                                                                                                                                                      parseInt(
                                                                                                                                                        args[i +
                                                                                                                                                               1]));
                                                                                                                                                i +=
                                                                                                                                                  2;
                                                                                                                                            }
                                                                                                                                            else
                                                                                                                                                if (args[i].
                                                                                                                                                      equals(
                                                                                                                                                        "-h") ||
                                                                                                                                                      args[i].
                                                                                                                                                      equals(
                                                                                                                                                        "-help")) {
                                                                                                                                                    usage(
                                                                                                                                                      true);
                                                                                                                                                }
                                                                                                                                                else {
                                                                                                                                                    if (input !=
                                                                                                                                                          null)
                                                                                                                                                        usage(
                                                                                                                                                          false);
                                                                                                                                                    input =
                                                                                                                                                      args[i];
                                                                                                                                                    i++;
                                                                                                                                                }
            }
            if (runBenchmark) {
                SunflowAPI.
                  runSystemCheck(
                    );
                new Benchmark(
                  ).
                  execute(
                    );
                return;
            }
            if (runRTBenchmark) {
                SunflowAPI.
                  runSystemCheck(
                    );
                new RealtimeBenchmark(
                  showFrame,
                  threads);
                return;
            }
            if (input ==
                  null)
                usage(
                  false);
            SunflowAPI.
              runSystemCheck(
                );
            if (frameStart <
                  frameStop &&
                  showFrame) {
                UI.
                  printWarning(
                    Module.
                      GUI,
                    ("Animations should not be rendered without -nogui - forcing G" +
                     "UI off anyway"));
                showFrame =
                  false;
            }
            if (frameStart <
                  frameStop &&
                  filename ==
                  null) {
                filename =
                  "output.#.png";
                UI.
                  printWarning(
                    Module.
                      GUI,
                    "Animation output was not specified - defaulting to: \"%s\"",
                    filename);
            }
            for (int frameNumber =
                   frameStart;
                 frameNumber <=
                   frameStop;
                 frameNumber++) {
                SunflowAPI api =
                  SunflowAPI.
                  create(
                    input,
                    frameNumber);
                if (api ==
                      null)
                    continue;
                if (noRender)
                    continue;
                if (resolutionW >
                      0 &&
                      resolutionH >
                      0) {
                    api.
                      parameter(
                        "resolutionX",
                        resolutionW);
                    api.
                      parameter(
                        "resolutionY",
                        resolutionH);
                }
                if (aaMin !=
                      -5 ||
                      aaMax !=
                      -5) {
                    api.
                      parameter(
                        "aa.min",
                        aaMin);
                    api.
                      parameter(
                        "aa.max",
                        aaMax);
                }
                if (bucketSize >
                      0)
                    api.
                      parameter(
                        "bucket.size",
                        bucketSize);
                if (bucketOrder !=
                      null)
                    api.
                      parameter(
                        "bucket.order",
                        bucketOrder);
                api.
                  parameter(
                    "aa.display",
                    showAA);
                api.
                  parameter(
                    "threads",
                    threads);
                api.
                  parameter(
                    "threads.lowPriority",
                    lowPriority);
                if (bakingName !=
                      null) {
                    api.
                      parameter(
                        "baking.instance",
                        bakingName);
                    api.
                      parameter(
                        "baking.viewdep",
                        bakeViewdep);
                }
                if (filterType !=
                      null)
                    api.
                      parameter(
                        "filter",
                        filterType);
                if (noGI)
                    api.
                      parameter(
                        "gi.engine",
                        "none");
                else
                    if (pathGI >
                          0) {
                        api.
                          parameter(
                            "gi.engine",
                            "path");
                        api.
                          parameter(
                            "gi.path.samples",
                            pathGI);
                    }
                if (noCaustics)
                    api.
                      parameter(
                        "caustics",
                        "none");
                api.
                  parameter(
                    "sampler",
                    sampler);
                api.
                  options(
                    SunflowAPI.
                      DEFAULT_OPTIONS);
                if (shaderOverride !=
                      null) {
                    api.
                      shader(
                        "ambocc",
                        shaderOverride);
                    api.
                      shaderOverride(
                        "ambocc",
                        true);
                }
                Display display;
                String currentFilename =
                  filename !=
                  null
                  ? filename.
                  replace(
                    "#",
                    String.
                      format(
                        "%04d",
                        frameNumber))
                  : null;
                if (showFrame) {
                    display =
                      new FrameDisplay(
                        currentFilename);
                }
                else {
                    if (currentFilename !=
                          null &&
                          currentFilename.
                          endsWith(
                            ".exr")) {
                        try {
                            display =
                              new OpenExrDisplay(
                                currentFilename,
                                "zip",
                                "float");
                        }
                        catch (Exception e) {
                            e.
                              printStackTrace(
                                );
                            return;
                        }
                    }
                    else
                        if (currentFilename !=
                              null &&
                              currentFilename.
                              equals(
                                "imgpipe")) {
                            display =
                              new ImgPipeDisplay(
                                );
                        }
                        else
                            display =
                              new FileDisplay(
                                currentFilename);
                }
                api.
                  render(
                    SunflowAPI.
                      DEFAULT_OPTIONS,
                    display);
            }
        }
        else {
            MetalLookAndFeel.
              setCurrentTheme(
                new DefaultMetalTheme(
                  ));
            SunflowGUI gui =
              new SunflowGUI(
              );
            gui.
              setVisible(
                true);
            Dimension screenRes =
              Toolkit.
              getDefaultToolkit(
                ).
              getScreenSize(
                );
            if (screenRes.
                  getWidth(
                    ) <=
                  DEFAULT_WIDTH ||
                  screenRes.
                  getHeight(
                    ) <=
                  DEFAULT_HEIGHT)
                gui.
                  setExtendedState(
                    MAXIMIZED_BOTH);
            gui.
              tileWindowMenuItem.
              doClick(
                );
            SunflowAPI.
              runSystemCheck(
                );
        }
    }
    public SunflowGUI() { super();
                          currentFile = null;
                          api = null;
                          initGUI();
                          pack();
                          setLocationRelativeTo(
                            null);
                          newFileMenuItemActionPerformed(
                            null);
                          UI.set(this); }
    private void initGUI() { setTitle("Sunflow v" +
                                      SunflowAPI.
                                        VERSION);
                             setDefaultCloseOperation(
                               EXIT_ON_CLOSE);
                             { desktop = new JDesktopPane(
                                           );
                               getContentPane(
                                 ).add(desktop,
                                       BorderLayout.
                                         CENTER);
                               Dimension screenRes =
                                 Toolkit.
                                 getDefaultToolkit(
                                   ).
                                 getScreenSize(
                                   );
                               if (screenRes.
                                     getWidth(
                                       ) <=
                                     DEFAULT_WIDTH ||
                                     screenRes.
                                     getHeight(
                                       ) <=
                                     DEFAULT_HEIGHT)
                                   desktop.
                                     setPreferredSize(
                                       new Dimension(
                                         640,
                                         480));
                               else
                                   desktop.
                                     setPreferredSize(
                                       new Dimension(
                                         DEFAULT_WIDTH,
                                         DEFAULT_HEIGHT));
                               { imagePanelFrame =
                                   new JInternalFrame(
                                     );
                                 desktop.
                                   add(
                                     imagePanelFrame);
                                 { jPanel1 =
                                     new JPanel(
                                       );
                                   FlowLayout jPanel1Layout =
                                     new FlowLayout(
                                     );
                                   jPanel1Layout.
                                     setAlignment(
                                       FlowLayout.
                                         LEFT);
                                   jPanel1.
                                     setLayout(
                                       jPanel1Layout);
                                   imagePanelFrame.
                                     getContentPane(
                                       ).
                                     add(
                                       jPanel1,
                                       BorderLayout.
                                         NORTH);
                                   { renderButton =
                                       new JButton(
                                         );
                                     jPanel1.
                                       add(
                                         renderButton);
                                     renderButton.
                                       setText(
                                         "Render");
                                     renderButton.
                                       addActionListener(
                                         new ActionListener(
                                           ) {
                                             public void actionPerformed(ActionEvent evt) {
                                                 renderMenuItemActionPerformed(
                                                   evt);
                                             }
                                         });
                                   }
                                   { iprButton =
                                       new JButton(
                                         );
                                     jPanel1.
                                       add(
                                         iprButton);
                                     iprButton.
                                       setText(
                                         "IPR");
                                     iprButton.
                                       addActionListener(
                                         new ActionListener(
                                           ) {
                                             public void actionPerformed(ActionEvent evt) {
                                                 iprMenuItemActionPerformed(
                                                   evt);
                                             }
                                         });
                                   } }
                                 { imagePanel =
                                     new ImagePanel(
                                       );
                                   imagePanelFrame.
                                     getContentPane(
                                       ).
                                     add(
                                       imagePanel,
                                       BorderLayout.
                                         CENTER);
                                 }
                                 imagePanelFrame.
                                   pack(
                                     );
                                 imagePanelFrame.
                                   setResizable(
                                     true);
                                 imagePanelFrame.
                                   setMaximizable(
                                     true);
                                 imagePanelFrame.
                                   setVisible(
                                     true);
                                 imagePanelFrame.
                                   setTitle(
                                     "Image");
                                 imagePanelFrame.
                                   setIconifiable(
                                     true);
                               }
                               { editorFrame =
                                   new JInternalFrame(
                                     );
                                 desktop.
                                   add(
                                     editorFrame);
                                 editorFrame.
                                   setTitle(
                                     "Script Editor");
                                 editorFrame.
                                   setMaximizable(
                                     true);
                                 editorFrame.
                                   setResizable(
                                     true);
                                 editorFrame.
                                   setIconifiable(
                                     true);
                                 { jScrollPane1 =
                                     new JScrollPane(
                                       );
                                   editorFrame.
                                     getContentPane(
                                       ).
                                     add(
                                       jScrollPane1,
                                       BorderLayout.
                                         CENTER);
                                   jScrollPane1.
                                     setVerticalScrollBarPolicy(
                                       ScrollPaneConstants.
                                         VERTICAL_SCROLLBAR_ALWAYS);
                                   jScrollPane1.
                                     setPreferredSize(
                                       new Dimension(
                                         360,
                                         280));
                                   { editorTextArea =
                                       new JTextArea(
                                         );
                                     jScrollPane1.
                                       setViewportView(
                                         editorTextArea);
                                     editorTextArea.
                                       setFont(
                                         new java.awt.Font(
                                           "Monospaced",
                                           0,
                                           12));
                                     editorTextArea.
                                       setTransferHandler(
                                         new SceneTransferHandler(
                                           ));
                                   } }
                                 { jPanel3 =
                                     new JPanel(
                                       );
                                   editorFrame.
                                     getContentPane(
                                       ).
                                     add(
                                       jPanel3,
                                       BorderLayout.
                                         SOUTH);
                                   FlowLayout jPanel3Layout =
                                     new FlowLayout(
                                     );
                                   jPanel3Layout.
                                     setAlignment(
                                       FlowLayout.
                                         RIGHT);
                                   jPanel3.
                                     setLayout(
                                       jPanel3Layout);
                                   { buildButton =
                                       new JButton(
                                         );
                                     jPanel3.
                                       add(
                                         buildButton);
                                     buildButton.
                                       setText(
                                         "Build Scene");
                                     buildButton.
                                       addActionListener(
                                         new ActionListener(
                                           ) {
                                             public void actionPerformed(ActionEvent evt) {
                                                 buildMenuItemActionPerformed(
                                                   evt);
                                             }
                                         });
                                   } }
                                 editorFrame.
                                   pack(
                                     );
                                 editorFrame.
                                   setVisible(
                                     true);
                               }
                               { consoleFrame =
                                   new JInternalFrame(
                                     );
                                 desktop.
                                   add(
                                     consoleFrame);
                                 consoleFrame.
                                   setIconifiable(
                                     true);
                                 consoleFrame.
                                   setMaximizable(
                                     true);
                                 consoleFrame.
                                   setResizable(
                                     true);
                                 consoleFrame.
                                   setTitle(
                                     "Console");
                                 { jScrollPane2 =
                                     new JScrollPane(
                                       );
                                   consoleFrame.
                                     getContentPane(
                                       ).
                                     add(
                                       jScrollPane2,
                                       BorderLayout.
                                         CENTER);
                                   jScrollPane2.
                                     setVerticalScrollBarPolicy(
                                       ScrollPaneConstants.
                                         VERTICAL_SCROLLBAR_ALWAYS);
                                   jScrollPane2.
                                     setPreferredSize(
                                       new Dimension(
                                         360,
                                         100));
                                   { consoleTextArea =
                                       new JTextArea(
                                         );
                                     jScrollPane2.
                                       setViewportView(
                                         consoleTextArea);
                                     consoleTextArea.
                                       setFont(
                                         new java.awt.Font(
                                           "Monospaced",
                                           0,
                                           12));
                                     consoleTextArea.
                                       setEditable(
                                         false);
                                   } }
                                 { jPanel4 =
                                     new JPanel(
                                       );
                                   consoleFrame.
                                     getContentPane(
                                       ).
                                     add(
                                       jPanel4,
                                       BorderLayout.
                                         SOUTH);
                                   BorderLayout jPanel4Layout =
                                     new BorderLayout(
                                     );
                                   jPanel4.
                                     setLayout(
                                       jPanel4Layout);
                                   { jPanel6 =
                                       new JPanel(
                                         );
                                     BorderLayout jPanel6Layout =
                                       new BorderLayout(
                                       );
                                     jPanel6.
                                       setLayout(
                                         jPanel6Layout);
                                     jPanel4.
                                       add(
                                         jPanel6,
                                         BorderLayout.
                                           CENTER);
                                     jPanel6.
                                       setBorder(
                                         BorderFactory.
                                           createEmptyBorder(
                                             5,
                                             5,
                                             5,
                                             0));
                                     { taskProgressBar =
                                         new JProgressBar(
                                           );
                                       jPanel6.
                                         add(
                                           taskProgressBar);
                                       taskProgressBar.
                                         setEnabled(
                                           false);
                                       taskProgressBar.
                                         setString(
                                           "");
                                       taskProgressBar.
                                         setStringPainted(
                                           true);
                                       taskProgressBar.
                                         setOpaque(
                                           false);
                                     } }
                                   { jPanel5 =
                                       new JPanel(
                                         );
                                     FlowLayout jPanel5Layout =
                                       new FlowLayout(
                                       );
                                     jPanel5Layout.
                                       setAlignment(
                                         FlowLayout.
                                           RIGHT);
                                     jPanel5.
                                       setLayout(
                                         jPanel5Layout);
                                     jPanel4.
                                       add(
                                         jPanel5,
                                         BorderLayout.
                                           EAST);
                                     { taskCancelButton =
                                         new JButton(
                                           );
                                       jPanel5.
                                         add(
                                           taskCancelButton);
                                       taskCancelButton.
                                         setText(
                                           "Cancel");
                                       taskCancelButton.
                                         setEnabled(
                                           false);
                                       taskCancelButton.
                                         addActionListener(
                                           new ActionListener(
                                             ) {
                                               public void actionPerformed(ActionEvent evt) {
                                                   UI.
                                                     taskCancel(
                                                       );
                                               }
                                           });
                                     }
                                     { clearConsoleButton =
                                         new JButton(
                                           );
                                       jPanel5.
                                         add(
                                           clearConsoleButton);
                                       clearConsoleButton.
                                         setText(
                                           "Clear");
                                       clearConsoleButton.
                                         addActionListener(
                                           new ActionListener(
                                             ) {
                                               public void actionPerformed(ActionEvent evt) {
                                                   clearConsole(
                                                     );
                                               }
                                           });
                                     } } }
                                 consoleFrame.
                                   pack(
                                     );
                                 consoleFrame.
                                   setVisible(
                                     true);
                               } }
                             { jMenuBar1 =
                                 new JMenuBar(
                                   );
                               setJMenuBar(
                                 jMenuBar1);
                               { fileMenu =
                                   new JMenu(
                                     );
                                 jMenuBar1.
                                   add(
                                     fileMenu);
                                 fileMenu.
                                   setText(
                                     "File");
                                 { newFileMenuItem =
                                     new JMenuItem(
                                       );
                                   fileMenu.
                                     add(
                                       newFileMenuItem);
                                   newFileMenuItem.
                                     setText(
                                       "New");
                                   newFileMenuItem.
                                     setAccelerator(
                                       KeyStroke.
                                         getKeyStroke(
                                           "ctrl N"));
                                   newFileMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               newFileMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 }
                                 { openFileMenuItem =
                                     new JMenuItem(
                                       );
                                   fileMenu.
                                     add(
                                       openFileMenuItem);
                                   openFileMenuItem.
                                     setText(
                                       "Open ...");
                                   openFileMenuItem.
                                     setAccelerator(
                                       KeyStroke.
                                         getKeyStroke(
                                           "ctrl O"));
                                   openFileMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               openFileMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 }
                                 { saveMenuItem =
                                     new JMenuItem(
                                       );
                                   fileMenu.
                                     add(
                                       saveMenuItem);
                                   saveMenuItem.
                                     setText(
                                       "Save");
                                   saveMenuItem.
                                     setAccelerator(
                                       KeyStroke.
                                         getKeyStroke(
                                           "ctrl S"));
                                   saveMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               saveCurrentFile(
                                                 currentFile);
                                           }
                                       });
                                 }
                                 { saveAsMenuItem =
                                     new JMenuItem(
                                       );
                                   fileMenu.
                                     add(
                                       saveAsMenuItem);
                                   saveAsMenuItem.
                                     setText(
                                       "Save As ...");
                                   saveAsMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               saveAsMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 }
                                 { jSeparator2 =
                                     new JSeparator(
                                       );
                                   fileMenu.
                                     add(
                                       jSeparator2);
                                 }
                                 { exitMenuItem =
                                     new JMenuItem(
                                       );
                                   fileMenu.
                                     add(
                                       exitMenuItem);
                                   exitMenuItem.
                                     setText(
                                       "Exit");
                                   exitMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               System.
                                                 exit(
                                                   0);
                                           }
                                       });
                                 } }
                               { sceneMenu =
                                   new JMenu(
                                     );
                                 jMenuBar1.
                                   add(
                                     sceneMenu);
                                 sceneMenu.
                                   setText(
                                     "Scene");
                                 { buildMenuItem =
                                     new JMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       buildMenuItem);
                                   buildMenuItem.
                                     setText(
                                       "Build");
                                   buildMenuItem.
                                     setAccelerator(
                                       KeyStroke.
                                         getKeyStroke(
                                           "ctrl B"));
                                   buildMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               if (sceneMenu.
                                                     isEnabled(
                                                       ))
                                                   buildMenuItemActionPerformed(
                                                     evt);
                                           }
                                       });
                                 }
                                 { autoBuildMenuItem =
                                     new JCheckBoxMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       autoBuildMenuItem);
                                   autoBuildMenuItem.
                                     setText(
                                       "Build on open");
                                   autoBuildMenuItem.
                                     setSelected(
                                       true);
                                 }
                                 { jSeparator3 =
                                     new JSeparator(
                                       );
                                   sceneMenu.
                                     add(
                                       jSeparator3);
                                 }
                                 { renderMenuItem =
                                     new JMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       renderMenuItem);
                                   renderMenuItem.
                                     setText(
                                       "Render");
                                   renderMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               renderMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 }
                                 { iprMenuItem =
                                     new JMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       iprMenuItem);
                                   iprMenuItem.
                                     setText(
                                       "IPR");
                                   iprMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               iprMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 }
                                 { clearLogMenuItem =
                                     new JCheckBoxMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       clearLogMenuItem);
                                   clearLogMenuItem.
                                     setText(
                                       "Auto Clear Log");
                                   clearLogMenuItem.
                                     setToolTipText(
                                       "Clears the console before building or rendering");
                                   clearLogMenuItem.
                                     setSelected(
                                       true);
                                 }
                                 { jSeparator4 =
                                     new JSeparator(
                                       );
                                   sceneMenu.
                                     add(
                                       jSeparator4);
                                 }
                                 { textureCacheClearMenuItem =
                                     new JMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       textureCacheClearMenuItem);
                                   textureCacheClearMenuItem.
                                     setText(
                                       "Clear Texture Cache");
                                   textureCacheClearMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               textureCacheClearMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 }
                                 { smallTrianglesMenuItem =
                                     new JCheckBoxMenuItem(
                                       );
                                   sceneMenu.
                                     add(
                                       smallTrianglesMenuItem);
                                   smallTrianglesMenuItem.
                                     setText(
                                       "Low Mem Triangles");
                                   smallTrianglesMenuItem.
                                     setToolTipText(
                                       ("Load future meshes using a low memory footprint triangle rep" +
                                        "resentation"));
                                   smallTrianglesMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               smallTrianglesMenuItemActionPerformed(
                                                 evt);
                                           }
                                       });
                                 } }
                               { imageMenu =
                                   new JMenu(
                                     );
                                 jMenuBar1.
                                   add(
                                     imageMenu);
                                 imageMenu.
                                   setText(
                                     "Image");
                                 { resetZoomMenuItem =
                                     new JMenuItem(
                                       );
                                   imageMenu.
                                     add(
                                       resetZoomMenuItem);
                                   resetZoomMenuItem.
                                     setText(
                                       "Reset Zoom");
                                   resetZoomMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               imagePanel.
                                                 reset(
                                                   );
                                           }
                                       });
                                 }
                                 { fitWindowMenuItem =
                                     new JMenuItem(
                                       );
                                   imageMenu.
                                     add(
                                       fitWindowMenuItem);
                                   fitWindowMenuItem.
                                     setText(
                                       "Fit to Window");
                                   fitWindowMenuItem.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               imagePanel.
                                                 fit(
                                                   );
                                           }
                                       });
                                 }
                                 { jSeparator1 =
                                     new JSeparator(
                                       );
                                   imageMenu.
                                     add(
                                       jSeparator1);
                                 }
                                 { jMenuItem4 =
                                     new JMenuItem(
                                       );
                                   imageMenu.
                                     add(
                                       jMenuItem4);
                                   jMenuItem4.
                                     setText(
                                       "Save Image ...");
                                   jMenuItem4.
                                     addActionListener(
                                       new ActionListener(
                                         ) {
                                           public void actionPerformed(ActionEvent evt) {
                                               JFileChooser fc =
                                                 new JFileChooser(
                                                 ".");
                                               fc.
                                                 setFileFilter(
                                                   new FileFilter(
                                                     ) {
                                                       public String getDescription() {
                                                           return "Image File";
                                                       }
                                                       public boolean accept(File f) {
                                                           return f.
                                                             isDirectory(
                                                               ) ||
                                                             f.
                                                             getName(
                                                               ).
                                                             endsWith(
                                                               ".png") ||
                                                             f.
                                                             getName(
                                                               ).
                                                             endsWith(
                                                               ".tga");
                                                       }
                                                   });
                                               if (fc.
                                                     showSaveDialog(
                                                       SunflowGUI.this) ==
                                                     JFileChooser.
                                                       APPROVE_OPTION) {
                                                   String filename =
                                                     fc.
                                                     getSelectedFile(
                                                       ).
                                                     getAbsolutePath(
                                                       );
                                                   imagePanel.
                                                     save(
                                                       filename);
                                               }
                                           }
                                       });
                                 } }
                               { windowMenu =
                                   new JMenu(
                                     );
                                 jMenuBar1.
                                   add(
                                     windowMenu);
                                 windowMenu.
                                   setText(
                                     "Window");
                               }
                               { imageWindowMenuItem =
                                   new JMenuItem(
                                     );
                                 windowMenu.
                                   add(
                                     imageWindowMenuItem);
                                 imageWindowMenuItem.
                                   setText(
                                     "Image");
                                 imageWindowMenuItem.
                                   setAccelerator(
                                     KeyStroke.
                                       getKeyStroke(
                                         "ctrl 1"));
                                 imageWindowMenuItem.
                                   addActionListener(
                                     new ActionListener(
                                       ) {
                                         public void actionPerformed(ActionEvent evt) {
                                             selectFrame(
                                               imagePanelFrame);
                                         }
                                     }); }
                               { editorWindowMenuItem =
                                   new JMenuItem(
                                     );
                                 windowMenu.
                                   add(
                                     editorWindowMenuItem);
                                 editorWindowMenuItem.
                                   setText(
                                     "Script Editor");
                                 editorWindowMenuItem.
                                   setAccelerator(
                                     KeyStroke.
                                       getKeyStroke(
                                         "ctrl 2"));
                                 editorWindowMenuItem.
                                   addActionListener(
                                     new ActionListener(
                                       ) {
                                         public void actionPerformed(ActionEvent evt) {
                                             selectFrame(
                                               editorFrame);
                                         }
                                     }); }
                               { consoleWindowMenuItem =
                                   new JMenuItem(
                                     );
                                 windowMenu.
                                   add(
                                     consoleWindowMenuItem);
                                 consoleWindowMenuItem.
                                   setText(
                                     "Console");
                                 consoleWindowMenuItem.
                                   setAccelerator(
                                     KeyStroke.
                                       getKeyStroke(
                                         "ctrl 3"));
                                 consoleWindowMenuItem.
                                   addActionListener(
                                     new ActionListener(
                                       ) {
                                         public void actionPerformed(ActionEvent evt) {
                                             selectFrame(
                                               consoleFrame);
                                         }
                                     }); }
                               { jSeparator5 =
                                   new JSeparator(
                                     );
                                 windowMenu.
                                   add(
                                     jSeparator5);
                               }
                               { tileWindowMenuItem =
                                   new JMenuItem(
                                     );
                                 windowMenu.
                                   add(
                                     tileWindowMenuItem);
                                 tileWindowMenuItem.
                                   setText(
                                     "Tile");
                                 tileWindowMenuItem.
                                   setAccelerator(
                                     KeyStroke.
                                       getKeyStroke(
                                         "ctrl T"));
                                 tileWindowMenuItem.
                                   addActionListener(
                                     new ActionListener(
                                       ) {
                                         public void actionPerformed(ActionEvent evt) {
                                             tileWindowMenuItemActionPerformed(
                                               evt);
                                         }
                                     }); }
                             } }
    private void newFileMenuItemActionPerformed(ActionEvent evt) {
        if (evt !=
              null) {
            
        }
        String template =
          ("import org.sunflow.core.*;\nimport org.sunflow.core.accel.*;" +
           "\nimport org.sunflow.core.camera.*;\nimport org.sunflow.core" +
           ".primitive.*;\nimport org.sunflow.core.shader.*;\nimport org" +
           ".sunflow.image.Color;\nimport org.sunflow.math.*;\n\npublic " +
           "void build() {\n  // your code goes here\n\n}\n");
        editorTextArea.
          setText(
            template);
    }
    private void openFileMenuItemActionPerformed(ActionEvent evt) {
        JFileChooser fc =
          new JFileChooser(
          ".");
        fc.
          setFileFilter(
            new FileFilter(
              ) {
                public String getDescription() {
                    return "Scene File";
                }
                public boolean accept(File f) {
                    return f.
                      isDirectory(
                        ) ||
                      f.
                      getName(
                        ).
                      endsWith(
                        ".sc") ||
                      f.
                      getName(
                        ).
                      endsWith(
                        ".java");
                }
            });
        if (fc.
              showOpenDialog(
                SunflowGUI.this) ==
              JFileChooser.
                APPROVE_OPTION) {
            final String f =
              fc.
              getSelectedFile(
                ).
              getAbsolutePath(
                );
            openFile(
              f);
        }
    }
    private void buildMenuItemActionPerformed(ActionEvent evt) {
        new Thread(
          ) {
            public void run() {
                setEnableInterface(
                  false);
                if (clearLogMenuItem.
                      isSelected(
                        ))
                    clearConsole(
                      );
                Timer t =
                  new Timer(
                  );
                t.
                  start(
                    );
                try {
                    api =
                      SunflowAPI.
                        compile(
                          editorTextArea.
                            getText(
                              ));
                }
                catch (NoClassDefFoundError e) {
                    UI.
                      printError(
                        Module.
                          GUI,
                        "Janino library not found. Please check command line.");
                    api =
                      null;
                }
                if (api !=
                      null) {
                    try {
                        if (currentFile !=
                              null) {
                            String dir =
                              new File(
                              currentFile).
                              getAbsoluteFile(
                                ).
                              getParent(
                                );
                            api.
                              addIncludeSearchPath(
                                dir);
                            api.
                              addIncludeSearchPath(
                                dir);
                        }
                        api.
                          build(
                            );
                    }
                    catch (Exception e) {
                        UI.
                          printError(
                            Module.
                              GUI,
                            "Build terminated abnormally: %s",
                            e.
                              getMessage(
                                ));
                        for (StackTraceElement elt
                              :
                              e.
                               getStackTrace(
                                 )) {
                            UI.
                              printInfo(
                                Module.
                                  GUI,
                                "       at %s",
                                elt.
                                  toString(
                                    ));
                        }
                        e.
                          printStackTrace(
                            );
                    }
                    t.
                      end(
                        );
                    UI.
                      printInfo(
                        Module.
                          GUI,
                        "Build time: %s",
                        t.
                          toString(
                            ));
                }
                setEnableInterface(
                  true);
            }
        }.
          start(
            );
    }
    private void clearConsole() { consoleTextArea.
                                    setText(
                                      null);
    }
    private void println(final String s) {
        SwingUtilities.
          invokeLater(
            new Runnable(
              ) {
                public void run() {
                    consoleTextArea.
                      append(
                        s +
                        "\n");
                }
            });
    }
    private void setEnableInterface(boolean enabled) {
        newFileMenuItem.
          setEnabled(
            enabled);
        openFileMenuItem.
          setEnabled(
            enabled);
        saveMenuItem.
          setEnabled(
            enabled);
        saveAsMenuItem.
          setEnabled(
            enabled);
        sceneMenu.
          setEnabled(
            enabled);
        buildButton.
          setEnabled(
            enabled);
        renderButton.
          setEnabled(
            enabled);
        iprButton.
          setEnabled(
            enabled);
    }
    public void print(Module m, PrintLevel level,
                      String s) { if (level ==
                                        PrintLevel.
                                          ERROR)
                                      JOptionPane.
                                        showMessageDialog(
                                          SunflowGUI.this,
                                          s,
                                          String.
                                            format(
                                              "Error - %s",
                                              m.
                                                name(
                                                  )),
                                          JOptionPane.
                                            ERROR_MESSAGE);
                                  println(
                                    UI.
                                      formatOutput(
                                        m,
                                        level,
                                        s));
    }
    public void taskStart(String s, int min,
                          int max) { currentTask =
                                       s;
                                     currentTaskLastP =
                                       -1;
                                     final int taskMin =
                                       min;
                                     final int taskMax =
                                       max;
                                     SwingUtilities.
                                       invokeLater(
                                         new Runnable(
                                           ) {
                                             public void run() {
                                                 taskProgressBar.
                                                   setEnabled(
                                                     true);
                                                 taskCancelButton.
                                                   setEnabled(
                                                     true);
                                                 taskProgressBar.
                                                   setMinimum(
                                                     taskMin);
                                                 taskProgressBar.
                                                   setMaximum(
                                                     taskMax);
                                                 taskProgressBar.
                                                   setValue(
                                                     taskMin);
                                                 taskProgressBar.
                                                   setString(
                                                     currentTask);
                                             }
                                         });
    }
    public void taskUpdate(int current) {
        final int taskCurrent =
          current;
        final String taskString =
          currentTask;
        SwingUtilities.
          invokeLater(
            new Runnable(
              ) {
                public void run() {
                    taskProgressBar.
                      setValue(
                        taskCurrent);
                    int p =
                      (int)
                        (100.0 *
                           taskProgressBar.
                           getPercentComplete(
                             ));
                    if (p >
                          currentTaskLastP) {
                        taskProgressBar.
                          setString(
                            taskString +
                            " [" +
                            p +
                            "%]");
                        currentTaskLastP =
                          p;
                    }
                }
            });
    }
    public void taskStop() { SwingUtilities.
                               invokeLater(
                                 new Runnable(
                                   ) {
                                     public void run() {
                                         taskProgressBar.
                                           setValue(
                                             taskProgressBar.
                                               getMinimum(
                                                 ));
                                         taskProgressBar.
                                           setString(
                                             "");
                                         taskProgressBar.
                                           setEnabled(
                                             false);
                                         taskCancelButton.
                                           setEnabled(
                                             false);
                                     }
                                 }); }
    private void renderMenuItemActionPerformed(ActionEvent evt) {
        new Thread(
          ) {
            public void run() {
                setEnableInterface(
                  false);
                if (clearLogMenuItem.
                      isSelected(
                        ))
                    clearConsole(
                      );
                if (api !=
                      null) {
                    api.
                      parameter(
                        "sampler",
                        "bucket");
                    api.
                      options(
                        SunflowAPI.
                          DEFAULT_OPTIONS);
                    api.
                      render(
                        SunflowAPI.
                          DEFAULT_OPTIONS,
                        imagePanel);
                }
                else
                    UI.
                      printError(
                        Module.
                          GUI,
                        "Nothing to render!");
                setEnableInterface(
                  true);
            }
        }.
          start(
            );
    }
    private void iprMenuItemActionPerformed(ActionEvent evt) {
        new Thread(
          ) {
            public void run() {
                setEnableInterface(
                  false);
                if (clearLogMenuItem.
                      isSelected(
                        ))
                    clearConsole(
                      );
                if (api !=
                      null) {
                    api.
                      parameter(
                        "sampler",
                        "ipr");
                    api.
                      options(
                        SunflowAPI.
                          DEFAULT_OPTIONS);
                    api.
                      render(
                        SunflowAPI.
                          DEFAULT_OPTIONS,
                        imagePanel);
                }
                else
                    UI.
                      printError(
                        Module.
                          GUI,
                        "Nothing to IPR!");
                setEnableInterface(
                  true);
            }
        }.
          start(
            );
    }
    private void textureCacheClearMenuItemActionPerformed(ActionEvent evt) {
        TextureCache.
          flush(
            );
    }
    private void smallTrianglesMenuItemActionPerformed(ActionEvent evt) {
        TriangleMesh.
          setSmallTriangles(
            smallTrianglesMenuItem.
              isSelected(
                ));
    }
    private void saveAsMenuItemActionPerformed(ActionEvent evt) {
        JFileChooser fc =
          new JFileChooser(
          ".");
        fc.
          setFileFilter(
            new FileFilter(
              ) {
                public String getDescription() {
                    return "Scene File";
                }
                public boolean accept(File f) {
                    return f.
                      isDirectory(
                        ) ||
                      f.
                      getName(
                        ).
                      endsWith(
                        ".java");
                }
            });
        if (fc.
              showSaveDialog(
                SunflowGUI.this) ==
              JFileChooser.
                APPROVE_OPTION) {
            String f =
              fc.
              getSelectedFile(
                ).
              getAbsolutePath(
                );
            if (!f.
                  endsWith(
                    ".java"))
                f +=
                  ".java";
            File file =
              new File(
              f);
            if (!file.
                  exists(
                    ) ||
                  JOptionPane.
                  showConfirmDialog(
                    SunflowGUI.this,
                    "This file already exists.\nOverwrite?",
                    "Warning",
                    JOptionPane.
                      YES_NO_OPTION) ==
                  JOptionPane.
                    YES_OPTION) {
                saveCurrentFile(
                  f);
            }
        }
    }
    private void saveCurrentFile(String filename) {
        if (filename ==
              null) {
            saveAsMenuItemActionPerformed(
              null);
            return;
        }
        FileWriter file;
        try {
            file =
              new FileWriter(
                filename);
            file.
              write(
                editorTextArea.
                  getText(
                    ));
            file.
              close(
                );
            currentFile =
              filename;
            UI.
              printInfo(
                Module.
                  GUI,
                "Saved current script to \"%s\"",
                filename);
        }
        catch (IOException e) {
            UI.
              printError(
                Module.
                  GUI,
                "Unable to save: \"%s\"",
                filename);
            e.
              printStackTrace(
                );
        }
    }
    private void selectFrame(JInternalFrame frame) {
        try {
            frame.
              setSelected(
                true);
            frame.
              setIcon(
                false);
        }
        catch (PropertyVetoException e) {
            e.
              printStackTrace(
                );
        }
    }
    private void tileWindowMenuItemActionPerformed(ActionEvent evt) {
        try {
            if (imagePanelFrame.
                  isIcon(
                    ))
                imagePanelFrame.
                  setIcon(
                    false);
            if (editorFrame.
                  isIcon(
                    ))
                editorFrame.
                  setIcon(
                    false);
            if (consoleFrame.
                  isIcon(
                    ))
                consoleFrame.
                  setIcon(
                    false);
            int width =
              desktop.
              getWidth(
                );
            int height =
              desktop.
              getHeight(
                );
            int widthLeft =
              width *
              7 /
              12;
            int widthRight =
              width -
              widthLeft;
            int pad =
              2;
            int pad2 =
              pad +
              pad;
            imagePanelFrame.
              reshape(
                pad,
                pad,
                widthLeft -
                  pad2,
                height -
                  pad2);
            editorFrame.
              reshape(
                pad +
                  widthLeft,
                pad,
                widthRight -
                  pad2,
                height /
                  2 -
                  pad2);
            consoleFrame.
              reshape(
                pad +
                  widthLeft,
                pad +
                  height /
                  2,
                widthRight -
                  pad2,
                height /
                  2 -
                  pad2);
        }
        catch (PropertyVetoException e) {
            e.
              printStackTrace(
                );
        }
    }
    private void openFile(String filename) {
        if (filename.
              endsWith(
                ".java")) {
            String code =
              "";
            FileReader file;
            try {
                file =
                  new FileReader(
                    filename);
                BufferedReader bf =
                  new BufferedReader(
                  file);
                while (true) {
                    String line;
                    line =
                      bf.
                        readLine(
                          );
                    if (line ==
                          null)
                        break;
                    code +=
                      line;
                    code +=
                      "\n";
                }
                file.
                  close(
                    );
                editorTextArea.
                  setText(
                    code);
            }
            catch (FileNotFoundException e) {
                UI.
                  printError(
                    Module.
                      GUI,
                    "Unable to load: \"%s\"",
                    filename);
                return;
            }
            catch (IOException e) {
                UI.
                  printError(
                    Module.
                      GUI,
                    "Unable to load: \"%s\"",
                    filename);
                return;
            }
            currentFile =
              filename;
            UI.
              printInfo(
                Module.
                  GUI,
                "Loaded script: \"%s\"",
                filename);
        }
        else
            if (filename.
                  endsWith(
                    ".sc")) {
                String template =
                  ("import org.sunflow.core.*;\nimport org.sunflow.core.accel.*;" +
                   "\nimport org.sunflow.core.camera.*;\nimport org.sunflow.core" +
                   ".primitive.*;\nimport org.sunflow.core.shader.*;\nimport org" +
                   ".sunflow.image.Color;\nimport org.sunflow.math.*;\n\npublic " +
                   "void build() {\n  parse(\"") +
                filename.
                  replace(
                    "\\",
                    "\\\\") +
                "\");\n}\n";
                editorTextArea.
                  setText(
                    template);
                currentFile =
                  null;
                UI.
                  printInfo(
                    Module.
                      GUI,
                    "Created template for \"%s\"",
                    filename);
            }
            else {
                UI.
                  printError(
                    Module.
                      GUI,
                    "Unknown file format selected");
                return;
            }
        editorTextArea.
          setCaretPosition(
            0);
        if (autoBuildMenuItem.
              isSelected(
                )) {
            buildMenuItemActionPerformed(
              null);
        }
    }
    private class SceneTransferHandler extends TransferHandler {
        public boolean importData(JComponent c,
                                  Transferable t) {
            if (!sceneMenu.
                  isEnabled(
                    ))
                return false;
            if (!canImport(
                   c,
                   t.
                     getTransferDataFlavors(
                       ))) {
                return false;
            }
            try {
                java.util.List files =
                  (java.util.List)
                    t.
                    getTransferData(
                      DataFlavor.
                        javaFileListFlavor);
                for (int i =
                       0;
                     i <
                       files.
                       size(
                         );
                     i++) {
                    final File file =
                      (File)
                        files.
                        get(
                          i);
                    String filename =
                      file.
                      getAbsolutePath(
                        );
                    if (filename.
                          endsWith(
                            ".sc") ||
                          filename.
                          endsWith(
                            ".java")) {
                        openFile(
                          filename);
                        break;
                    }
                }
            }
            catch (Exception exp) {
                exp.
                  printStackTrace(
                    );
            }
            return false;
        }
        public boolean canImport(JComponent c,
                                 DataFlavor[] flavors) {
            for (int i =
                   0;
                 i <
                   flavors.
                     length;
                 i++) {
                if (flavors[i].
                      isFlavorJavaFileListType(
                        ))
                    return true;
            }
            return false;
        }
        public SceneTransferHandler() { super(
                                          );
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1414698658000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1414698658000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAK1bC5AUxRnu3TsOOB53HA/xPA44DsLLXUEw6lnqsdxjjwNW" +
       "7g7kUI652b67uZudWWZm7xYUESoGi5QmpfiMIYlBjQpiYohalBZWKiLlI6VJ" +
       "rFgp8ZEqo0GSkJRKxUTz/z09++653S2p6r7Z6e7v/7r/v//+u3s4fJaMMQ2y" +
       "JKqrO/pV3fLRuOUbVFf6rB1Ravra2leGJMOk4YAqmWYnvOuR656u+PzLHwxU" +
       "eklZN5kqaZpuSZaia+YGaurqMA23k4rk2yaVRkyLVLYPSsOSP2Ypqr9dMa2G" +
       "djIhpalF6tsdCn6g4AcKfkbB35isBY0mUS0WCWALSbPM7eQW4mknZVEZ6Vlk" +
       "bjpIVDKkCIcJsR4Awjj8vRE6xRrHDTIn0Xe7z1kdvmeJ/8B9Wyt/WUIqukmF" +
       "onUgHRlIWCCkm0yM0EgvNczGcJiGu8kUjdJwBzUUSVV2Mt7dpMpU+jXJihk0" +
       "MUj4MhalBpOZHLmJMvbNiMmWbiS616dQNez8GtOnSv3Q1xnJvto9bMb30MFy" +
       "BYgZfZJMnSalQ4oWtsjszBaJPtavgQrQdGyEWgN6QlSpJsELUmXrTpW0fn+H" +
       "ZShaP1Qdo8dAikWqhaA41lFJHpL6aY9FZmbWC9lFUGs8GwhsYpHpmdUYEmip" +
       "OkNLKfo5u+6qO2/SWjUv4xymsor8x0Gj2oxGG2gfNagmU7vhxMXt90ozXrjd" +
       "SwhUnp5R2a7z7M3nrl1ae+IVu85FOeqs7x2kstUjH+qd/GZNYNEVJUhjXFQ3" +
       "FVR+Ws+Z+Yd4SUM8CjNvRgIRC31O4YkNL2++9Ql6xkvKg6RM1tVYBOxoiqxH" +
       "oopKjRaqUUOyaDhIxlMtHGDlQTIWntsVjdpv1/f1mdQKklKVvSrT2W8Yoj6A" +
       "wCEaC8+K1qc7z1HJGmDP8SghZCwksgZSM7H/sb8WCfq7TDB3vyRLmqLpfjBe" +
       "KhnygJ/Kek8vjO5ARDKGTL8cMy094jdjWp+qj/hNQ/Z32M8tXUEfmlT0mwSL" +
       "I/PKEY8HBrUmc0qrMBtadTVMjR75QGxV07mnel71Jkyc99ki5UlM4vEwqGmI" +
       "besGRnYI5ih4r4mLOm5s23Z7XQkYRXSkFIYFq9YBZy6wSdYDyYkcZO5KBmua" +
       "+fCWfb7zj11jW5Nf7HVztiYn7h/Zs3H3JV7iTXef2AF4VY7NQ+j0Es6tPnPa" +
       "5MKt2Pfx50fv3aUnJ1CaP+bzOrslzsu6zKE2dJmGwdMl4RfPkY71vLCr3ktK" +
       "YbKDg7MkMEjwHbWZMtLmZ4Pj67AvY6DDfboRkVQschxUuTVg6CPJN8wGJrPn" +
       "KaCUCWiw00E5L9kGbP/F0qlRzKfZNoNazugF86XNz5944NiDS67wprrdipSF" +
       "rINa9iSekjSSToNSeP/u/aG77zm7bwuzEKgxL5eAeswDMKVBZTCst72y/Z33" +
       "Th/6gzdpVRasbbFeVZHjgLEgKQUmvApOB3Vf36VF9LDSp0i9KkXj/G/F/GXH" +
       "Pr2z0tamCm8cY1g6OkDy/YWryK2vbv2ilsF4ZFxwkj1PVrMHYGoSudEwpB3I" +
       "I77nrVkPnJR+BP4QfJCp7KTMrRDWM8KG3s9UtZjlvoyyZZjNiWaVxdmbmezX" +
       "t0H0IvEkasZ1M2Xy/We92rv3w/OsR1nTJ8dykdG+23/4oerA1WdY+6QdY+vZ" +
       "8WynAzFGsu3yJyKfeevKfuslY7tJpcwDmI2SGkNr6YZF23SiGghy0srTF2B7" +
       "tWlIzNOazDmUIjZzBiWdHTxjbXwuz5g0F+IoL4fUwt1+S+akYX7RNnmk5AtC" +
       "vNFPjaoPf3Loiz37LveivscMI3UYlcpkvXUxjJO+e/ieWRMOvP89ZuWElDLo" +
       "K5j4OpbPx2wh02+JRcZGDWUYVjqYCSaLuyzok6JJatwik1Y3NTd2tXf2bAqu" +
       "7mx1t4WQoURgZR3mS79/V9V7Qw99fMR2xJmKz6hMbz+w/2vfnQe8KcHUvKx4" +
       "JrWNHVCxkZ1kj+zX8M8D6StMOKL4wl5QqwJ8VZ+TWNajUZzwc91oMRHNfz26" +
       "6/jPd+2zu1GVHks0Qah85O3/vea7//1TORa7EogTmf+z59iKbAto5RbQmsMC" +
       "8KEBNMTerxOoDx+vxawRs1WgssmOylqbgi2tnfi6JSeJiSjzIkhBTiKYRYKw" +
       "h87csj1MNkgcOxiSNKpeyipMh9FmwYLPHIE41tfGysQE5kNq4wTaBAS2YLbR" +
       "IhMHO2QD/CJCLnOkXZAmLVlBLHIej7qc6CuXyG2OSBpXrLWwJQpaNOKInJ4m" +
       "0il1F9jOBbYLBFIucMJgB+Wr1XJH3oz0Ljrl7lpdywWuFQgc5AK59thisFkM" +
       "ORfSOg65TgAZcQYNIgwIBFfFLIv7fujE1LRO2GXu4tZzcesF4gwurnzQUcEK" +
       "fCO7KyLEUUMC1Fi2ItjYDIhhF0K6jsNeJ4CNc9gpfYq1CfyKPpJqNy6cF0Ha" +
       "wME3CMBv4uBV4JRoQeg4Ih0cvUOAfkv2iKwcZUSWQurksJ0C2D0cdjquxXqB" +
       "vJdA6uICugQCvsMFTKNhBTgXhL8Y0kaOv1GAv4/jT1UisM0uCH4WpE0cfpMA" +
       "fr9j3iMJZGcuTclyQO7GeT0Xdb1A1PedictV0QwbHOoIq04ThqGIAcEBqyKW" +
       "+i1Im7nUzQKpBxy7svWTQLzLXe3dHLZbAHsfh61gamHOLR9o9DhbOPQWAfSD" +
       "js8MU3PI0qPOEM1MG6LVdqH7IrQS0g1c3A0CcT/m4maYsCtTOw0FYjyVmpnL" +
       "0aw06YEBKg+t0uOjL0uXQLqRU7hRQOEQp3AhnsTBnjMgyQM0oFLJKMDBbOVC" +
       "tgqEPJbtYFbk4XJ7OGyPAPYJx+Ua1IQdgK5H8uRcA2kbB98mAD/CwcczK3Om" +
       "4B1i0DpIEgeVBKBPO1M+aboJJetGv48fzvjMHSb0whdM1BKLxVCgl4vtFYg9" +
       "5pi1HQpchj9dQgE/JJlDygLI5zhkpYy20q73pw79w+50wxw7LMA+nk535Sh0" +
       "McKkHJIKIF906FqSORTAnZ2ajFCiYmxcnPs4dp8A+yXHHyF2yND7wRzNVfx4" +
       "PMt5pFRwn1T9XGy/QOzL2ZPq0lEmFTqEAQ47IIA95UwqKWbpq2KKGs5Ts2j/" +
       "CgdXBOCvOZyVaL7+BbU7yGEHBbC/47CT7bg0T+R6SEMceUiA/BZHntSbORKj" +
       "uBeVA6sC4D867sWUYceah3vBcYhw0IgA9E/OONgLbie49EaDSrm3NU6pWOQC" +
       "SBoXqQlEvusYP48sUlH/LIbG8EHn0LoA+n0OXcVcTMDGz2vWopOJcvSoAP0v" +
       "6U5mRR5OZjuH3C6A/IhDpm5jl+O7rWLcWkgGxzUEuJ84tgKzJq/+z4FkclBT" +
       "APqpMxWZZecFi2NgcVhLAPsPxwRNaZg2mgUEETGOHBMg/8sZXUTOExfNbJjj" +
       "DgtwP+O4lXqUas2wv8oTG2fHCMceEWCfd2aHRkcKgEb7jXPouAD6Sw49ro/j" +
       "juI/ZkPawTF3CDC/cgyNbbhhhUocwkzL2pC4rl8o7GYu7ObcwjwljgHKMQP8" +
       "toXj44hLOfK0r0vdRe3ionYJRI3LENUJCzW+LxPD4m7hFg57iwB2gmM5KbDt" +
       "kmmF8P0oZ3K7OfZuAfZkjl0iRZXEMVFqiMjv2RpDQVtQ3OUQb1Hqib/HgZuT" +
       "I+LEq8Sgc/mNB6izRJfG7PD00N4DB8PrH1nm5TcOa8B2YGd0sUqHeWRry5yB" +
       "SGl3e2vZNXnydH//408+a7255Er7GHax+BQ6s+HJvX+r7rx6YFsBN3qzM/qU" +
       "Cfn42sOnWhbId3lJSeKSIOvmP71RQ/rVQLlBYTOldaZdENQmrKAKlX4xpD3c" +
       "CvZkWgHTaW6NeplGMWu01Zq8/PGkKHg1Zq12jTZY5np1WD0lDZE98zPapVwa" +
       "eRZjNsciY2ImbD5yQZUO60o4+1YJX3hq0vuIpyxHeB+PfLN9NMh8sZGwGzT7" +
       "5uHgo/Pe2H1w3gde4ukm4xRzo2Q0Gv05PodIafPPw++deWvSrKfYdWtpr2Ta" +
       "Wsz8jiT7M5G0rz9YBydG2aiW5dTRiqgz3a8cbRzw5L9MpVq//X0BehfP5XzS" +
       "I6zXru/M7alJBxpQdY3ixWSaL/cpui/xiQ0UxnMR9NTY7C/HsnoXo2l2KWvF" +
       "bDUYlIxEbN6gvdm5b1KbIlGL3X3ufO6CX1312MHT7JIrbkOtdBGzFrNLwDoj" +
       "koKBjGfpqCbKnHE19PU120Ltv1kmyjyxi+QOl7IuzEIw/RRNsVq6ggUQu5aQ" +
       "Eh59l2RF30JinnQfz872fNIIzJFhWKF8jWyYm/CZ8dviwr0Hs+stUpsRvNgY" +
       "IbB83YjQcAFdaoSu8JCpJCtkGq1LiHcDI0ZdSGOk4OkFf50ZzRXP+mpgu5+z" +
       "3l8864gLa/x+yDNokZq0bWbxlCEoKjnKKR8tyqhjLmUjmBl4mpyyMSuA3UJg" +
       "9Qxn90wxA1rGaOxyobgbs5323bdmqYU4hBXA6jhnd7wYdgsYg9tc2OHFgmcv" +
       "7GxNajVp6H4TMVd+RNlnOXh3fJITPSkkmum2S+x7ZcdB1OQKAoP1a/VwLLli" +
       "5IwUg/UhHNt2DPWSSrnDpdt3Y7YfVgKmlAJ6uhIIv857+nqhPd3uBPosIm9h" +
       "TB5wYflDzO7FQBbC+Q5LMgphChvOktOc6em8mSaNh/P7qQu/n2F20ILAFvh1" +
       "RcOSVYjRwHJXcpYTPJs3wVT5j7uUPYnZo7AptcdOjxYw764BRuc5s/PFzDvb" +
       "zf7Chd4zmD1lkVnp54TF+9mrCCkdb3O2/xbH+XkXzngY7vk1rOQpR6bFE24D" +
       "otWccHXxhF9yIfwbzF60yELhjVLx9FuAdi2nX1s8/VMu9F/F7GWLzM99J1c8" +
       "d7Dv0jrOva547m+6cP89Zm+AfacfvhXPeRlwbeCcG4rhbC8K77hwxkNiz9sW" +
       "qUDOgeRRUAEslwM7/tVHadZXH3mwJHcxJh+4sMTTYs9pi0wwKW5VnPvmfBkG" +
       "gBm/1i7NutbOX/efuDA8g9lHFpmb/YlI8fqH/XspP50uzTqdzl//51x4/xuz" +
       "v8OS4QTsAnrxtG/Lo4mr+fSP0Zhi4oQVzuQto2m/0r4kxoMD9l9cnJOgmP2f" +
       "XHrkowfb1t107rJH2LESbF2lnTsRZRzs8e2PqBkonibNFaI5WGWti76c/PT4" +
       "+c4pGTvbq0o5/5iZMh5r/g9VMEmKUDQAAA==");
}
