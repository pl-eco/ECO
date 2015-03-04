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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "PqukpxEAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
