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
    final private static int DEFAULT_WIDTH = 1024;
    final private static int DEFAULT_HEIGHT = 768;
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
                        SunflowGUI.
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
                                    SunflowGUI.
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
                                                SunflowGUI.
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
                                                                            SunflowGUI.
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
                                                                                SunflowGUI.
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
                                                                                    SunflowGUI.
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
                                                                                        SunflowGUI.
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
                                                                                            SunflowGUI.
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
                                                                                                SunflowGUI.
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
                                                                                                    SunflowGUI.
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
                                                                                                        SunflowGUI.
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
                                                                                                            SunflowGUI.
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
                                                                                                                SunflowGUI.
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
                                                                                                                    SunflowGUI.
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
                                                                                                                        SunflowGUI.
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
                                                                                                                            SunflowGUI.
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
                                                                                                                                SunflowGUI.
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
                                                                                                                                SunflowGUI.
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
                                                                                                                                            SunflowGUI.
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
                                                                                                                                                SunflowGUI.
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
                                                                                                                                                    SunflowGUI.
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
                                                                                                                                                    SunflowGUI.
                                                                                                                                                      usage(
                                                                                                                                                        true);
                                                                                                                                                }
                                                                                                                                                else {
                                                                                                                                                    if (input !=
                                                                                                                                                          null)
                                                                                                                                                        SunflowGUI.
                                                                                                                                                          usage(
                                                                                                                                                            false);
                                                                                                                                                    input =
                                                                                                                                                      args[i];
                                                                                                                                                    i++;
                                                                                                                                                }
            }
            if (runBenchmark) {
                SunflowAPI.
                  runSystemCheck();
                new Benchmark(
                  ).
                  execute();
                return;
            }
            if (runRTBenchmark) {
                SunflowAPI.
                  runSystemCheck();
                new RealtimeBenchmark(
                  showFrame,
                  threads);
                return;
            }
            if (input ==
                  null)
                SunflowGUI.
                  usage(
                    false);
            SunflowAPI.
              runSystemCheck();
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
                              printStackTrace();
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
              getDefaultToolkit().
              getScreenSize();
            if (screenRes.
                  getWidth() <=
                  DEFAULT_WIDTH ||
                  screenRes.
                  getHeight() <=
                  DEFAULT_HEIGHT)
                gui.
                  setExtendedState(
                    MAXIMIZED_BOTH);
            gui.
              tileWindowMenuItem.
              doClick();
            SunflowAPI.
              runSystemCheck();
        }
    }
    public SunflowGUI() { super();
                          currentFile = null;
                          api = null;
                          this.initGUI();
                          this.pack();
                          this.setLocationRelativeTo(
                                 null);
                          this.newFileMenuItemActionPerformed(
                                 null);
                          UI.set(this); }
    private void initGUI() { this.setTitle(
                                    "Sunflow v" +
                                    SunflowAPI.
                                      VERSION);
                             this.setDefaultCloseOperation(
                                    EXIT_ON_CLOSE);
                             { desktop = new JDesktopPane(
                                           );
                               this.getContentPane().
                                 add(
                                   desktop,
                                   BorderLayout.
                                     CENTER);
                               Dimension screenRes =
                                 Toolkit.
                                 getDefaultToolkit().
                                 getScreenSize();
                               if (screenRes.
                                     getWidth() <=
                                     DEFAULT_WIDTH ||
                                     screenRes.
                                     getHeight() <=
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
                                     getContentPane().
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
                                                 SunflowGUI.this.
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
                                                 SunflowGUI.this.
                                                   iprMenuItemActionPerformed(
                                                     evt);
                                             }
                                         });
                                   } }
                                 { imagePanel =
                                     new ImagePanel(
                                       );
                                   imagePanelFrame.
                                     getContentPane().
                                     add(
                                       imagePanel,
                                       BorderLayout.
                                         CENTER);
                                 }
                                 imagePanelFrame.
                                   pack();
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
                                     getContentPane().
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
                                     getContentPane().
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
                                                 SunflowGUI.this.
                                                   buildMenuItemActionPerformed(
                                                     evt);
                                             }
                                         });
                                   } }
                                 editorFrame.
                                   pack();
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
                                     getContentPane().
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
                                     getContentPane().
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
                                                     taskCancel();
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
                                                   SunflowGUI.this.
                                                     clearConsole();
                                               }
                                           });
                                     } } }
                                 consoleFrame.
                                   pack();
                                 consoleFrame.
                                   setVisible(
                                     true);
                               } }
                             { jMenuBar1 =
                                 new JMenuBar(
                                   );
                               this.setJMenuBar(
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
                                               SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                                     isEnabled())
                                                   SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                               SunflowGUI.this.
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
                                                 reset();
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
                                                 fit();
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
                                                             isDirectory() ||
                                                             f.
                                                             getName().
                                                             endsWith(
                                                               ".png") ||
                                                             f.
                                                             getName().
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
                                                     getSelectedFile().
                                                     getAbsolutePath();
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
                                             SunflowGUI.this.
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
                                             SunflowGUI.this.
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
                                             SunflowGUI.this.
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
                                             SunflowGUI.this.
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
                      isDirectory() ||
                      f.
                      getName().
                      endsWith(
                        ".sc") ||
                      f.
                      getName().
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
              getSelectedFile().
              getAbsolutePath();
            this.
              openFile(
                f);
        }
    }
    private void buildMenuItemActionPerformed(ActionEvent evt) {
        new Thread(
          ) {
            public void run() {
                SunflowGUI.this.
                  setEnableInterface(
                    false);
                if (clearLogMenuItem.
                      isSelected())
                    SunflowGUI.this.
                      clearConsole();
                Timer t =
                  new Timer(
                  );
                t.
                  start();
                try {
                    api =
                      SunflowAPI.
                        compile(
                          editorTextArea.
                            getText());
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
                              getAbsoluteFile().
                              getParent();
                            api.
                              addIncludeSearchPath(
                                dir);
                            api.
                              addIncludeSearchPath(
                                dir);
                        }
                        api.
                          build();
                    }
                    catch (Exception e) {
                        UI.
                          printError(
                            Module.
                              GUI,
                            "Build terminated abnormally: %s",
                            e.
                              getMessage());
                        for (StackTraceElement elt
                              :
                              e.
                               getStackTrace()) {
                            UI.
                              printInfo(
                                Module.
                                  GUI,
                                "       at %s",
                                elt.
                                  toString());
                        }
                        e.
                          printStackTrace();
                    }
                    t.
                      end();
                    UI.
                      printInfo(
                        Module.
                          GUI,
                        "Build time: %s",
                        t.
                          toString());
                }
                SunflowGUI.this.
                  setEnableInterface(
                    true);
            }
        }.
          start();
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
                                                name()),
                                          JOptionPane.
                                            ERROR_MESSAGE);
                                  this.println(
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
                           getPercentComplete());
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
                                               getMinimum());
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
                SunflowGUI.this.
                  setEnableInterface(
                    false);
                if (clearLogMenuItem.
                      isSelected())
                    SunflowGUI.this.
                      clearConsole();
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
                SunflowGUI.this.
                  setEnableInterface(
                    true);
            }
        }.
          start();
    }
    private void iprMenuItemActionPerformed(ActionEvent evt) {
        new Thread(
          ) {
            public void run() {
                SunflowGUI.this.
                  setEnableInterface(
                    false);
                if (clearLogMenuItem.
                      isSelected())
                    SunflowGUI.this.
                      clearConsole();
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
                SunflowGUI.this.
                  setEnableInterface(
                    true);
            }
        }.
          start();
    }
    private void textureCacheClearMenuItemActionPerformed(ActionEvent evt) {
        TextureCache.
          flush();
    }
    private void smallTrianglesMenuItemActionPerformed(ActionEvent evt) {
        TriangleMesh.
          setSmallTriangles(
            smallTrianglesMenuItem.
              isSelected());
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
                      isDirectory() ||
                      f.
                      getName().
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
              getSelectedFile().
              getAbsolutePath();
            if (!f.
                  endsWith(
                    ".java"))
                f +=
                  ".java";
            File file =
              new File(
              f);
            if (!file.
                  exists() ||
                  JOptionPane.
                  showConfirmDialog(
                    SunflowGUI.this,
                    "This file already exists.\nOverwrite?",
                    "Warning",
                    JOptionPane.
                      YES_NO_OPTION) ==
                  JOptionPane.
                    YES_OPTION) {
                this.
                  saveCurrentFile(
                    f);
            }
        }
    }
    private void saveCurrentFile(String filename) {
        if (filename ==
              null) {
            this.
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
                  getText());
            file.
              close();
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
              printStackTrace();
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
              printStackTrace();
        }
    }
    private void tileWindowMenuItemActionPerformed(ActionEvent evt) {
        try {
            if (imagePanelFrame.
                  isIcon())
                imagePanelFrame.
                  setIcon(
                    false);
            if (editorFrame.
                  isIcon())
                editorFrame.
                  setIcon(
                    false);
            if (consoleFrame.
                  isIcon())
                consoleFrame.
                  setIcon(
                    false);
            int width =
              desktop.
              getWidth();
            int height =
              desktop.
              getHeight();
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
              printStackTrace();
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
                        readLine();
                    if (line ==
                          null)
                        break;
                    code +=
                      line;
                    code +=
                      "\n";
                }
                file.
                  close();
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
              isSelected()) {
            this.
              buildMenuItemActionPerformed(
                null);
        }
    }
    private class SceneTransferHandler extends TransferHandler {
        public boolean importData(JComponent c,
                                  Transferable t) {
            if (!sceneMenu.
                  isEnabled())
                return false;
            if (!this.
                  canImport(
                    c,
                    t.
                      getTransferDataFlavors())) {
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
                       size();
                     i++) {
                    final File file =
                      (File)
                        files.
                        get(
                          i);
                    String filename =
                      file.
                      getAbsolutePath();
                    if (filename.
                          endsWith(
                            ".sc") ||
                          filename.
                          endsWith(
                            ".java")) {
                        SunflowGUI.this.
                          openFile(
                            filename);
                        break;
                    }
                }
            }
            catch (Exception exp) {
                exp.
                  printStackTrace();
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
                      isFlavorJavaFileListType())
                    return true;
            }
            return false;
        }
        public SceneTransferHandler() { super();
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1414698658000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1YfWwUxxUf352/nZ5twBAgGBsTApjbOA1QcFTi+APOHOCe" +
           "PwKm1Ix35+4W9nY3\nu3Pnw0UpaSpMgtIENZFaqSFQIfHRpEFKKxopEFCSNg" +
           "2qlOSPRKoU2gqprdSmalSpRWr/6JuZ3du7\nvfOlAom93Zl53+/95j2/+jmq" +
           "ti20XLYj9IhJ7Ej/6Ai2bKL0a9i2x2BpSn6vun7k/E7dCKCqGAqo\nCkXhmG" +
           "xLCqZYUhUpOtCbs9B609COJDWDRkiORg5pGx1+w7GNJQwfP32l9alzofYAqo" +
           "6hMNZ1g2Kq\nGvqgRtI2Rc2xQziLpQxVNSmm2rQ3hu4heibdb+g2xTq1n0BP" +
           "omAM1Zgy40lRR8wVLoFwycQWTktc\nvDTCxQKHBRahWNWJ0pcXB5TdxZSgtk" +
           "MXLz0NTOrY5gSYwzUAq1fmrRbWlphqBi9MbDp65mIQhSdR\nWNVHGTMZLKEg" +
           "bxI1pUl6mlh2n6IQZRK16IQoo8RSsabOcqmTqNVWkzqmGYvYcWIbWpYdbLUz" +
           "JrG4\nTHcxhppkZpOVkalh5X2UUImmuF/VCQ0nwew2z2xh7hBbBwMbVFDMSm" +
           "CZuCShw6oOEW/3U+Rt7NoJ\nB4C0Nk1oysiLCukYFlCriKWG9aQ0Si1VT8LR" +
           "aiMDUihaOi9T5msTy4dxkkxRtMR/bkRswal67ghG\nQtEi/zHOCaK01Belgv" +
           "isb/vXiQs/vvYoz+2QQmSN6d8ARCt8RHGSIBbRZSII72QiL0b3ZZYHEILD\n" +
           "i3yHxZm+1VfGY395u12cWVbmzJ7pQ0SmU/LuU+3xb283UJCpUWcatsqCX2Q5" +
           "L4cRZ6c3Z0LVtuU5\nss2Iu3k9/qt9xy6RvwZQQxTVyIaWSUMetchG2lQ1Ym" +
           "0nOrEwJUoU1RNd6ef7UVQL7zFIebG6J5Gw\nCY2ikMaXagz+DS5KAAvmonp4" +
           "V/WE4b6bmKb4e85ECDXBf9SCUOgM4v/EL0W9UspIEwnLWFd1Q0qq\nUHeysU" +
           "EhWfYrRSQ7oyc0Y0ayLVkaFe/bx6MRlkQmRVsqk1cgzjHdWmeqqhjY+YtWg3" +
           "zfYWgKsabk\n87c/ODq485kTIiFYEjtWQQ14PLtGZfDimIV1G7JiB9YVcCyq" +
           "quL8FzKBIiTg0MNQmgBiTWtHDwwf\nPNEZhFwwZ0LgDXa0E9R2tBiUjX6vfq" +
           "Mc6mRIoiU/2T8XuXN+m0giaX6YLUvd+OFrN8/8s2ldAAXK\nYyCzDlC4gbEZ" +
           "YcCZx7Yuf9WU4//3Z3e98cnNzx7w6oeirpKyLqVkZdnpj4NlyEQBoPPYn7s3" +
           "HHwc\nTZwKoBDUOuAb1x+gY4VfRlF59rpQx2ypjaHGhGGlsca2XHxqoCnLmP" +
           "FWeII0s8dCkSsskD4FOUre\nebrmwU/fanyPW+wCarjgyholVJRni5cHYxYh" +
           "sP7ZD0d+8NLnc/t5EjhZQFGtaalZKMgc0Nzv0UBh\nagAOLEhd43raUNSEiq" +
           "c1wrLpv+HVPb/42/ebhds1WHGj1v3lDLz1ex9Dx25+698rOJsqmV0Mnh3e\n" +
           "MWHOAo9zn2XhI0yP3FMf3/ejX+OXAbcAK2x1lvDyR9w0xB0Z4f5dy58bfHsP" +
           "skcn8O6eJ63LXMNT\n8tFLyc7ME795k2vdiAvv88I47MJmr4gql70AhG5D4l" +
           "EMS2x3kcmebSwEi/3luwPbKWD28PXd32zW\nrv8HxE6CWBnuSHuPBaCRKwq1" +
           "c7q69nc33mk7+FEQBYZQg2ZgZQjzAkD1kHnETgHe5Mxtj3I9mmfq\n2JP7BX" +
           "FtlzpeyhV98Y9V/Hm/kz7s/QHfKQvdN9+Nx2/rub1fNB3H7x4QkNJafIsMQq" +
           "f15yPvkDWP\nPPfHMiBY43QsnsAAk1eEY7t4J+BV8bMXf3qFfrR+q5C3bn4I" +
           "8xOu23pmtn3r6yfvAr3afR7ws27J\nLvtGMKW+H+DNigCukianmKi30BcgFP" +
           "TJWDrzKltp4rm2Mp9rYTfXzjq5dtafawJmSkMKTjYz05oq\n53ylE3D8zb4X" +
           "Q7Hyiy1iz0BXFRnuh+vd0InTHsN2J89JPEMjrFOnzl0VcS8thgRchViFAo2z" +
           "xxCF\nvhCYW3QAGEG0lxROC5aahq4jy3H39vHOq++PvzInAr22wkhQSDUlf+" +
           "f3fzgcfP7GtKDzd16+w6dW\nnPvTG7fjC0VyivZ0VUmHWEgjWlRuUNhk5dFR" +
           "SQI//e76jlefjN/iGjG6rwNMTxuGRrBIuh722C4C\ntPFLq5h/PFacGgOQEl" +
           "ed1LhaNjXYY8fdpQDcXQUx4nDNTLv4wsCC+Jb9T/N6qochA9u7vYSG0Y69\n" +
           "VUEIVs8fujyzKXnNgSv/uHGNrOGIWKfaMBn1WckyDXcBzRf4Etn1aeI0v9FD" +
           "09gWxeOfVEoHkaL5\ngjvoK3mHSsx/Wyo51HXYyvJFwRIbbvesMzgxT1flm7" +
           "QKeTzEdPZAZWq6+0Lsgz0v89ScF6/KpLiP\nz+y18dN3fktvcT5e28OoO3Kl" +
           "DSz43aP92ifZlprLr6QDqHYSNcvOzDyBtQzrQCbBs7Y7SMNcXbRf\nPK6J2a" +
           "Q3j47L/TVWINbfcBXiZIgWISTvsdRcFeKRsUrxL0CBl6pjPokN5wANNaInaY" +
           "qfGTNFKPdR\nFIQUYa8pZ6lMeYiWhVkDNyBUBsM8d0906aoRyc/bDBBLws8r" +
           "V6jMhZUrykLE/G6Fve+xxzEwT2bK\nCN0hv9rLt2yDaZPyJmv2l4t//sj507" +
           "cYFpk5mKLqeOU+tLkHiFuhzlkqR1QlEjNkrEUHzt5o/PhU\nZtOwQNN7Cg5E" +
           "B45eHm6qO3vyOL/2nIKvLxg5ne/aLLZ2e9c++zlB0cBdj19bex7q+Wr3w5s3" +
           "bNoM\nQJoQlcZ9Ml3BX8+zB8z/9SAwyi8gtrDXA+CD/y8AQxYtLDeyucnQ4O" +
           "lrumvLCrHVR1fccLEgrioC\nCf4nKLfzyIg/Qk3Je1/bvzJ3cuwFDr+QBHh2" +
           "lv+1AfwtBpR899IxLzeX14fo8usTb/1si3tBFY0u\nJXdPj9id31tsY878H5" +
           "xD454OFAAA");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698658000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALWcD3QUx33H504S+mv0B5BtDBIIYTDgu4iAjZETLIT+nHwS" +
       "sv5hC7BY7Y1OK+3t\nHrt70kEINvELOLhpTENe8xfjPCfGrilucUtax65p7N" +
       "rY8auT92o3fTVN7LRJX0Lq/HkNbtP3+pvZ\n2bvbvZuVbs/w3s6tdmdmP9+Z" +
       "3/zmz87yzBVUomtomagHjANxrAfaB/sFTceRdlnQ9SG4NCa+UlLe\n/+Tdiu" +
       "pHvjDySxEDVYdFPRgRDCEoRYKhHa1JDa2Pq/KBqKwaAZw0AlPyZpZfT3hzVo" +
       "a7Tl2oO/JE\ncaMflYRRtaAoqiEYkqp0yDimG6gmPCXMCMGEIcnBsKQbrWF0" +
       "HVYSsXZV0Q1BMfT96DAqCqMFcZHk\naaCVYevhQXh4MC5oQixIHx/sp4+FHB" +
       "Zp2BAkBUfaUo+DlBvsKQGbpRvIjg2ZlJGbIyCHEoDqFSnV\nptosqfGiMyO3" +
       "HTr9VBGqHkXVkjJIMhNBiQHPG0VVMRwbx5reFongyCiqVTCODGJNEmTpIH3q" +
       "KKrT\npagiGAkN6wNYV+UZErFOT8SxRp9pXQyjKpFo0hKioWqpMpqQsByx/i" +
       "qZkIUoyK5PyzbldpLrILBC\nAjBtQhCxlaR4WlKgxhudKVIam++GCJC0NIaN" +
       "STX1qGJFgAuozqxLWVCiwUFDk5QoRC1RE/AUAy3l\nZkrKOi6I00IUjxnoBm" +
       "e8fvMWxCqnBUGSGGiJMxrNCWppqaOWMupnff1/P3zm6y/eRW27OIJFmfBX\n" +
       "QKIGR6IBPIE1rIjYTHg1ETgZui+xzI8QRF7iiGzGaVt9YTj8879rNOPclCPO" +
       "zvEpLBpjYt+JxoFP\ndamoiGCUxVVdIpVvU06bQz+705qMQ6utT+VIbgasmy" +
       "8N/MN9Dz6Nf+FHFSG0QFTlRAzsqFZUY3FJ\nxloXVrAmGDgSQuVYibTT+yFU" +
       "CudhMHnz6s6JCR0bIVQs00sLVPo3FNEEZEGKqBzOJWVCtc7jgjFJ\nz5NxhF" +
       "ApHOhuODqR+Y/+Gqg1OKnGcFAQBUVS1GBUgnYnqrdG8Az5DQaCekKZkNXZoK" +
       "6JwUHzvGs4\nFCBGFDfQHe7JXRInCVvdrM9HnJ2z0cpg792qHMHamPjk+68f" +
       "6rj7cw+bBkGMmKkyUEU6T+Tz0awW\nk7zN0oeym4ZWCP6q6pbBvT37Hm4qgm" +
       "qPzxaDcBK1CQjZAztEtT3dVEPUq4lgLzd8c/exwNUnt5n2\nEuR71JypK986" +
       "+8bp31at8yN/bndHhIDDrSDZ9BMfmXJjzc4Gkiv/Xx3vPf/2G++uTTcVAzVn" +
       "teDs\nlKQFNjmLXFNFHAGfls7+iRuri3ahkRN+VAzNGlwZ5Qcv0eB8hq0ltl" +
       "pejWgpDaPKCVWLCTK5Zbmi\nCmNSU2fTV6gt1NDzRVA5lcQ0l0AlXTRN1fwl" +
       "d5fESVhv2g6pbYcK6jWvPrTgY+98t/IVWiyWg63O\n6MIGsWE219q0sQxpGM" +
       "P1d7/c/8UvXTm2m1oKMxUD+rXEuCyJSUhyczoJtFMZfAWpyOZhJaZGpAlJ\n" +
       "GJcxsbg/VK9u+atf/nGNWTUyXLFqdsPcGaSv37gdPfjG/b9voNn4RNJPpGWk" +
       "o5lqFqVzbtM04QDh\nSB754fKvvCp8A9wYuA5dOoipN0BUGaLlGKTlvo6GAc" +
       "e9FhI0Qd4bOKafo1ceEw89HW1K7L/0N5S6\nUsjs3jOroVeIt5o1T4JVpHSv" +
       "d7bebkGfhHibXurbUyO/9L+Q4yjkKEJvqO/UwD0kbZXIYpeU/svF\n79Xv+0" +
       "ER8neiClkVIp0CtX9UDoaH9UnwLMn4truocdXMlpGQSka0EJayAkhm/HU7wN" +
       "3Cb/6dpE9P\nt5yx8Q1nwq/v/AYtAG7Dz9GlOfI5+OLwqatvGpdpPukWSFKv" +
       "TGa7TRgHpdNueXumdsGzj8X8qHQU\n1YhspDYiyAli56MwsNCt4RuM5mz37Y" +
       "MEs0dsTXmYZc7Wn/FYZ9tPu2s4J7HJeZWjud9ISnsjHF2s\na+pyNnfq2c16" +
       "JkiBEIyJolire+/0E78/cmyLnxh3yQxBh1KpScfrS5Cx3NFnvrS88uSPHyFO" +
       "HIy/\nmGa9jT6+mYZrzIZeZAC8pAigsTSuSTPQI0PT1+n4MGmg63Z0dLYNh4" +
       "fGdoV2DHUD0A2ZQ3RNikFX\nP0M94PtHm154bfixY2av4WI2tlRj4gP/9uPp" +
       "oi9cHDfTOW3DEflEwxP/cf79gcWmhzHHhKuyhmWZ\nacxxIS386jhpbSvdnk" +
       "Bjv7x+5TOHBy4zojr76KYDZgA/O/A9vObOz/8kR+dcBCNX00+TcCMJ7jKb\n" +
       "1GZu09uabRTdzCi6cxgFOWmDiqPXh3PUKDnvJEEXCbqhFhdatdjdEerqHiKX" +
       "+xyUI/OnrCJXb4Ij\nxChDWZSInuzNhvNRLkAqneoXFCx/nN68HhofHR4F9F" +
       "kYmwd66D0H4f15Eq6Go4cR9nAIJ0gAQ/uq\nqUFRg56FPLfFQrrehpSO4OCK" +
       "5sm1io1JrbFpLq6YxYWTktELs86QgWMW1xIbl3XXQaV4oAozqjCH\nymBUlV" +
       "ODmMxuoXfZaEHV2wvLuu+gSniwsl5G1cuh+hSjYhbVMn+LOpQnzUo4+hhNH4" +
       "fmM1bNwdAQ\nuurtCcNgXR8gLbIhmfccTA95YNrJmHZymD7HmCqmLGPZlJct" +
       "HfdgS/2MqZ/D9Gi2LbXkZ0sn8qRa\nC8c9jOoeDtWfMqraCcnYBV2BOuup8X" +
       "05T7Rb4BhgaAMctFMMrQ56IVwA22MeKnOQsQ1y2L6VXZmb\n86vMb+dJtQGO" +
       "IUY1xKF6hlEtIeM8taBCO5sn3no4hhneMAfvPMNbjCMSFEgBdM/lSbcOjhFG" +
       "N8Kh\n+1tGt0iKCdFCiu75POGWw7GLwe3iwF20HNpsistiqs1icvD8vQe3cS" +
       "/juZfD85rl9JmldWowJrSI\nltqIyCheg+E2jeJAu5Qn2ho47mNo93HQ/tFq" +
       "mqaVeSR7y4P9jzKyUQ7Z24ysmloY7Z890r3joc/c\nzeh2c+jeZXSlEaxPG2" +
       "rcorrBRrXDvJljVHg5T6bNcOxhTHs4TD9lTPV6TJDlIU2C6Z6MdWezXG5D\n" +
       "bJ/E4vR2Nclpnv+eJ+fH4NjLOPdyOH/BOG8kbxcSGm4XxEncLmNB8+RBfumh" +
       "x7qfId7PQfxNdo+V\nGhPNr8f6rQc/MsaoxjhUHzKqWg3r2BhV1ZinAvufPN" +
       "GWwbGPoe3LjeZDDK2cttZ8PK7PlydOExwC\nwxE4OKVWD5B2HinzV7VogK3B" +
       "B/QDOpRPIJSK5WAr8zAhGWds4xy2hZbrMCckt817QuKrzpMmCIfI\naEQOzR" +
       "JGUyOSBhhWo4W4C1+9h/KKMMIIh3CZvbw2z7+8lntYEsCMBnNoVlnlZQj6dD" +
       "tZ35PzncT5\nmj2M/ycY1wSHax3jqiZc/ZoaBR+hb2cvgbM6powIDrb1Hrxp" +
       "lLFFOWwt2d40tbAzL2/q2+ihG5pk\nVJMcqi2WNxUShro9IcmRgkz/Dg9uTG" +
       "KIEgdxm1VwUtxT3+jLY3Ex1QKmGNQUB6qTQS00lzA8cXXl\nydUMxzTjmuZw" +
       "9TKu68Zz1eW8sPo89Iwyw5I5WEMMq1wXsZJfzzjsofZiDCfGwdlt1Z454B+C" +
       "IVib\nhoXcxWTddXDtyZPrZjgUxqVwuMYt/8XmSJ7ARA8zJJWBqRwwiYHV0Q" +
       "6y3aTL2+VPeegY44wsziGL\n77d1jKkB6twd434PhrWf0ezn0Mwymsy18tTy" +
       "7/zWyn3JPLka4NAYl8bhOmy1P/CheVfbA3kCrYBD\nZ0A6B+izllOnfipvpK" +
       "Me6s5gSAYH6Y8sp6ALM7gta8Y4L9/5eQ8DhwTjSnC4vmjZFOHyRHXSg0eY\n" +
       "YVQzHKqvMqoaNY6VTkn2RvY1D050lpHNcsget5yogmc9g33Tg6tKMrAkB+wM" +
       "AyubYFTz7gOfypOm\nEY4DjOYAh+ac5RLoCw8YA6deLSzOwskeIT/rgegQIz" +
       "rEIfpryyeICQ2GVQapPIsp4929uTfRwXPB\nA8+nGc+nOTwvOHiGYFIxX54X" +
       "PSy3HWY8hzk8L1tNLoMnLOhGP7nueFfte8WDAT/AAB7gAFxiAEVC\nXEpNXT" +
       "JXEtiuu7b+kIPmdRca81b2G/BbMrfZ+KznrcixcjGsYy1k7YYl+xeW83aR0r" +
       "0Lx+79ddVR\n4eW9fraXqR8agaHGb5XxDFshMZ9ZT3KybQXspftm01tpjj/1" +
       "ZxeMH6zfau6CWMffz+FMuG7r6YON\nW8894mEDYKNDmzPr2pmb7imalF7z06" +
       "295s6crC3B9kSt9v04FcCT0JQh266cFSlzqSPVdiscR5i5\nHHGaS7rybbXq" +
       "p7VKgm6zatNby3wZldwTN+/uhEHduArjTEEhOfrec6TJ2I7m+xkJ3jVQSUIX" +
       "otiZ\nTfGMKkXSxjjnsrO1z4v84fuRXTh5L3SWCT/70QnX0OKMTUN0lx4p/6" +
       "ce3bFo4I7dD1E7KRdkSdD7\n0hXllyLkzAdWsZpve6nMxsQ1ey98cPFFvIbu" +
       "liuT9BFBa9OiObZdZ6T5tfA07n1n4hTd7Fk8Luim\nUTj3q2dvR7ftMqdFsz" +
       "BVlGTOSCf/3KLkOtqcNrOV1NSHlp/6P04VdJpuxUALZKxEzU3QxG/6rjJb\n" +
       "Idn6zfi2waj5/HZZVTDZhmnrKAOSGkh9BwA3k7kAfT8yAenDyP2b+cbsL3e5" +
       "V0mCUjB0kcCY7GA8\njbn3jnbE4gbd7XnwO9c/d+eTpy7TnW5JGAKXUVvauG" +
       "kLJK4DyyNfiASkSCCsioIc2vH4xcofnkjc\n1mM6tusyIoR2HHq2p6rs8UeO" +
       "UgfDTLA8Yys8+7t0RtD60tu+CHe1gdo9bwvf2nL7xzds3HLrphZo\n0IIW1W" +
       "lh/s6lrOpJhP+C2DHwruT8P9M+4AOPPoD2lUuhVr9v2q35m+UDaEfpgtbgcm" +
       "8FCW4C5ycp\nkgHq7eT+ZYWQQ5oitghRlLUIwSX32btg+tIxIMyCr5mBYUig" +
       "jVpbBzmnAta6iNtAgtUGanAMzs08\n+sGDqFoMRxyaby5EM9kHyOYMRVlzho" +
       "9K8yYXzWSN1Q9W2+icKrmLnnO51030J0HscSb6+LUSfZeL\n6B0k+ISBltkW" +
       "It0Vf7IQxTCiLzrHFJ/z1CjDLvdIN+EPkd0LGQtgDvyeQvDXAvZ5hn/ea4Vl" +
       "dZaU\nfcRF1ygJBs19zYohO9ykf6gQSZtAyvNM0vP5SiL5vU8Rx13wiQX5yb" +
       "qkjo0OhXS/qXmAQ8k+j0ro\nJyetoOBVpuRVrhJnn15kbjW2KmdZrplLqLlX" +
       "jSTSQ4qc05tQcz+pnXBqfsKtatmlrMjOVL8EQwda\n1Y7imXNp1q14NoPKN1" +
       "nxvOm1eHLOqOmsto/iH3KR9iAJDpBpHEyJBw1Bc8o7WIi8NUB5mcm7PG95\n" +
       "aTtmAh52EXCcBJ81YL4HAobjMNhy2u+cK55uCmDAUnSFKbgybwWZgH/icu8k" +
       "Cb4AY0qz9NW4A/3R\nQpzINkC+ytCv5utE5tuRfd1F3WkSfMVAy+1v+tx7sq" +
       "8WIvlOhIrLTcnm77WQfMZFMtkU6v8W5JHx\nxtVd75xbU9309oDOpUzv0mul" +
       "9zkXvd8hwV8YaC13L5a7+r8sRH0XqG5g6huulfqXXNSTZUX/CwZa\nnXvHnL" +
       "v0Odc552jbxU1MetO1kv59F+lvkeAStG37Kx93yW5rmXNKbgGprUxyq1fJuf" +
       "v+f3YR+q8k\n+CcDVROh7enldYe0twuRthEksS8airO+aMirNnNvaKVCfuoi" +
       "8uck+ImBKnVMVkCsXbCZAt8rRGA7\nCGMbdouzNux+VOb6gYvA35HgioFWZn" +
       "/e4G6yvypE9jqQy94vF2e9Xy7MZP/AF0s/3fN/CKMKa9bs\n0DTnBs7U17q2" +
       "/w4gntqlbN8hYJqYLSFZWFtlW1el/12Jte6eMP/DkjHx3rO7VyQfGXqULtKW" +
       "iLJw\n8CDJpiKMSs0v3GmuZO1+JTc3K6+30LPnRr7753dY7yZqSLA4Y2E5s6" +
       "bMnayL7QVRlSoIh82ZhZ+I\nx8met12CppDFVF5Sg302C0VwO2dpuc32n8Fg" +
       "9omw9enwmHhyz6XFv6ld9DwtljL6HTHEIM/7hMs3\nv3SRnYwCOkyTM38zTY" +
       "58ekvXW/m2BSZATGRh/P8BVofUbTRHAAA=");
}
