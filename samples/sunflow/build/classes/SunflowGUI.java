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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAAKVYa2wUVRS+Oy1tqYWW8hARSl+gBd1RE41YgkJtobhApZXo" +
           "Eqm3M3fbgdmZceZuu1Trg8RA/EGMVgSjjRoUH7xiJGoMCX8UjMYEYkz8oah/" +
           "NCKJ/PAR3+fcO7OzO7utMTbp7cy955x7nt8508MXyTTPJcsd29w5aNo8zrI8" +
           "vt28Mc53OsyLr0/c2ENdj+kdJvW8Ptjr15qP1/78+xNDdQqpSJLZ1LJsTrlh" +
           "W95m5tnmMNMTpDbc7TRZ2uOkLrGdDlM1ww1TTRgeb0+Qy/JYOWlNBCqooIIK" +
           "KqhCBXV1SAVMM5iVSXcgB7W4dz95iMQSpMLRUD1OmgqFONSlaV9Mj7AAJFTh" +
           "+xYwSjBnXdKYs13aXGTw08vV8We21b1ZRmqTpNawelEdDZTgcEmS1KRZeoC5" +
           "3mpdZ3qSzLIY03uZa1DTGBV6J0m9ZwxalGdclnMSbmYc5oo7Q8/VaGibm9G4" +
           "7ebMSxnM1IO3aSmTDoKt80JbpYVduA8GVhugmJuiGgtYyncYls7J4ihHzsbW" +
           "O4AAWCvTjA/ZuavKLQobpF7GzqTWoNrLXcMaBNJpdgZu4WTBpELR1w7VdtBB" +
           "1s/J/ChdjzwCqunCEcjCydwomZAEUVoQiVJefC5uXLn3AWudpQiddaaZqH8V" +
           "MDVEmDazFHOZpTHJWLMssY/OO7lHIQSI50aIJc3bD1667ZqGU2ckzZUlaDYN" +
           "bGca79cODsw8u7CjbUUZqlHl2J6BwS+wXKR/j3/SnnWg8ublJOJhPDg8tfmD" +
           "ex55nV1QSHU3qdBsM5OGPJql2WnHMJm7llnMpZzp3WQ6s/QOcd5NKuE5YVhM" +
           "7m5KpTzGu0m5KbYqbPEOLkqBCHRRJTwbVsoOnh3Kh8Rz1iGE1MAvmUVI+QtE" +
           "/Mi/nKxSh+w0U6lGLcOyVchdRl1tSGWarXo07ZgQNS9jpUx7RPVcTe2Vz2vv" +
           "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" +
           "M49PXxIAZMFAmq+b8Ow/GD4zPoERAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/5ieNB5r/gtuKAqEOjQAAA==");
}
