package org.sunflow;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.sunflow.core.Camera;
import org.sunflow.core.CameraLens;
import org.sunflow.core.Display;
import org.sunflow.core.Geometry;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.Instance;
import org.sunflow.core.LightSource;
import org.sunflow.core.Modifier;
import org.sunflow.core.Options;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.RenderObject;
import org.sunflow.core.Scene;
import org.sunflow.core.SceneParser;
import org.sunflow.core.Shader;
import org.sunflow.core.Tesselatable;
import org.sunflow.core.ParameterList.InterpolationType;
import org.sunflow.core.parser.RA2Parser;
import org.sunflow.core.parser.RA3Parser;
import org.sunflow.core.parser.SCParser;
import org.sunflow.core.parser.ShaveRibParser;
import org.sunflow.core.parser.TriParser;
import org.sunflow.core.renderer.BucketRenderer;
import org.sunflow.core.renderer.ProgressiveRenderer;
import org.sunflow.core.renderer.SimpleRenderer;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.SearchPath;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class SunflowAPI {
    public static final String VERSION = "0.07.2";
    public static final String DEFAULT_OPTIONS = "::options";
    private Scene scene;
    private BucketRenderer bucketRenderer;
    private ProgressiveRenderer progressiveRenderer;
    private SearchPath includeSearchPath;
    private SearchPath textureSearchPath;
    private ParameterList parameterList;
    private RenderObjectMap renderObjects;
    private int currentFrame;
    public static void runSystemCheck() { final long RECOMMENDED_MAX_SIZE =
                                            800;
                                          long maxMb = Runtime.getRuntime(
                                                                 ).maxMemory(
                                                                     ) / 1048576;
                                          if (maxMb < RECOMMENDED_MAX_SIZE)
                                              UI.
                                                printError(
                                                  Module.
                                                    API,
                                                  ("JVM available memory is below %d MB (found %d MB only).\nPle" +
                                                   "ase make sure you launched the program with the -Xmx command" +
                                                   " line options."),
                                                  RECOMMENDED_MAX_SIZE,
                                                  maxMb);
                                          String compiler =
                                            System.
                                            getProperty(
                                              "java.vm.name");
                                          if (compiler ==
                                                null ||
                                                !(compiler.
                                                    contains(
                                                      "HotSpot") &&
                                                    compiler.
                                                    contains(
                                                      "Server")))
                                              UI.
                                                printError(
                                                  Module.
                                                    API,
                                                  ("You do not appear to be running Sun\'s server JVM\nPerforman" +
                                                   "ce may suffer"));
                                          UI.printDetailed(
                                               Module.
                                                 API,
                                               "Java environment settings:");
                                          UI.printDetailed(
                                               Module.
                                                 API,
                                               "  * Max memory available : %d MB",
                                               maxMb);
                                          UI.
                                            printDetailed(
                                              Module.
                                                API,
                                              "  * Virtual machine name : %s",
                                              compiler ==
                                                null
                                                ? "<unknown"
                                                : compiler);
                                          UI.
                                            printDetailed(
                                              Module.
                                                API,
                                              "  * Operating system     : %s",
                                              System.
                                                getProperty(
                                                  "os.name"));
                                          UI.
                                            printDetailed(
                                              Module.
                                                API,
                                              "  * CPU architecture     : %s",
                                              System.
                                                getProperty(
                                                  "os.arch"));
    }
    public SunflowAPI() { super();
                          reset(); }
    public final void reset() { scene = new Scene(
                                          );
                                bucketRenderer =
                                  new BucketRenderer(
                                    );
                                progressiveRenderer =
                                  new ProgressiveRenderer(
                                    );
                                includeSearchPath =
                                  new SearchPath(
                                    "include");
                                textureSearchPath =
                                  new SearchPath(
                                    "texture");
                                parameterList =
                                  new ParameterList(
                                    );
                                renderObjects =
                                  new RenderObjectMap(
                                    );
                                currentFrame =
                                  1; }
    public final String getUniqueName(String prefix) {
        int counter =
          1;
        String name;
        do  {
            name =
              String.
                format(
                  "%s_%d",
                  prefix,
                  counter);
            counter++;
        }while(renderObjects.
                 has(
                   name)); 
        return name;
    }
    public final void parameter(String name,
                                String value) {
        parameterList.
          addString(
            name,
            value);
    }
    public final void parameter(String name,
                                boolean value) {
        parameterList.
          addBoolean(
            name,
            value);
    }
    public final void parameter(String name,
                                int value) {
        parameterList.
          addInteger(
            name,
            value);
    }
    public final void parameter(String name,
                                float value) {
        parameterList.
          addFloat(
            name,
            value);
    }
    public final void parameter(String name,
                                Color value) {
        parameterList.
          addColor(
            name,
            value);
    }
    public final void parameter(String name,
                                Point3 value) {
        parameterList.
          addPoints(
            name,
            InterpolationType.
              NONE,
            new float[] { value.
                            x,
            value.
              y,
            value.
              z });
    }
    public final void parameter(String name,
                                Vector3 value) {
        parameterList.
          addVectors(
            name,
            InterpolationType.
              NONE,
            new float[] { value.
                            x,
            value.
              y,
            value.
              z });
    }
    public final void parameter(String name,
                                Matrix4 value) {
        parameterList.
          addMatrices(
            name,
            InterpolationType.
              NONE,
            value.
              asRowMajor(
                ));
    }
    public final void parameter(String name,
                                int[] value) {
        parameterList.
          addIntegerArray(
            name,
            value);
    }
    public final void parameter(String name,
                                String[] value) {
        parameterList.
          addStringArray(
            name,
            value);
    }
    public final void parameter(String name,
                                String type,
                                String interpolation,
                                float[] data) {
        InterpolationType interp;
        try {
            interp =
              InterpolationType.
                valueOf(
                  interpolation.
                    toUpperCase(
                      ));
        }
        catch (IllegalArgumentException e) {
            UI.
              printError(
                Module.
                  API,
                "Unknown interpolation type: %s -- ignoring parameter \"%s\"",
                interpolation,
                name);
            return;
        }
        if (type.
              equals(
                "float"))
            parameterList.
              addFloats(
                name,
                interp,
                data);
        else
            if (type.
                  equals(
                    "point"))
                parameterList.
                  addPoints(
                    name,
                    interp,
                    data);
            else
                if (type.
                      equals(
                        "vector"))
                    parameterList.
                      addVectors(
                        name,
                        interp,
                        data);
                else
                    if (type.
                          equals(
                            "texcoord"))
                        parameterList.
                          addTexCoords(
                            name,
                            interp,
                            data);
                    else
                        if (type.
                              equals(
                                "matrix"))
                            parameterList.
                              addMatrices(
                                name,
                                interp,
                                data);
                        else
                            UI.
                              printError(
                                Module.
                                  API,
                                "Unknown parameter type: %s -- ignoring parameter \"%s\"",
                                type,
                                name);
    }
    public void remove(String name) { renderObjects.
                                        remove(
                                          name);
    }
    public boolean update(String name) { boolean success =
                                           renderObjects.
                                           update(
                                             name,
                                             parameterList,
                                             this);
                                         parameterList.
                                           clear(
                                             success);
                                         return success;
    }
    public final void addIncludeSearchPath(String path) {
        includeSearchPath.
          addSearchPath(
            path);
    }
    public final void addTextureSearchPath(String path) {
        textureSearchPath.
          addSearchPath(
            path);
    }
    public final String resolveTextureFilename(String filename) {
        return textureSearchPath.
          resolvePath(
            filename);
    }
    public final String resolveIncludeFilename(String filename) {
        return includeSearchPath.
          resolvePath(
            filename);
    }
    public final void shader(String name,
                             Shader shader) {
        if (shader !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare shader \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                shader);
        }
        if (lookupShader(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                "Unable to update shader \"%s\" - shader object was not found",
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void modifier(String name,
                               Modifier modifier) {
        if (modifier !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare modifier \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                modifier);
        }
        if (lookupModifier(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                ("Unable to update modifier \"%s\" - modifier object was not f" +
                 "ound"),
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void geometry(String name,
                               PrimitiveList primitives) {
        if (primitives !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare geometry \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                primitives);
        }
        if (lookupGeometry(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                ("Unable to update geometry \"%s\" - geometry object was not f" +
                 "ound"),
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void geometry(String name,
                               Tesselatable tesselatable) {
        if (tesselatable !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare geometry \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                tesselatable);
        }
        if (lookupGeometry(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                ("Unable to update geometry \"%s\" - geometry object was not f" +
                 "ound"),
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void instance(String name,
                               String geoname) {
        if (geoname !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare instance \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            parameter(
              "geometry",
              geoname);
            renderObjects.
              put(
                name,
                new Instance(
                  ));
        }
        if (lookupInstance(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                ("Unable to update instance \"%s\" - instance object was not f" +
                 "ound"),
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void light(String name, LightSource light) {
        if (light !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare light \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                light);
        }
        if (lookupLight(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                ("Unable to update instance \"%s\" - instance object was not f" +
                 "ound"),
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void camera(String name,
                             CameraLens lens) {
        if (lens !=
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare camera \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                new Camera(
                  lens));
        }
        if (lookupCamera(
              name) !=
              null)
            update(
              name);
        else {
            UI.
              printError(
                Module.
                  API,
                "Unable to update camera \"%s\" - camera object was not found",
                name);
            parameterList.
              clear(
                true);
        }
    }
    public final void options(String name) {
        if (lookupOptions(
              name) ==
              null) {
            if (renderObjects.
                  has(
                    name)) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to declare options \"%s\", name is already in use",
                    name);
                parameterList.
                  clear(
                    true);
                return;
            }
            renderObjects.
              put(
                name,
                new Options(
                  ));
        }
        assert lookupOptions(
                 name) !=
          null;
        update(
          name);
    }
    public final Geometry lookupGeometry(String name) {
        return renderObjects.
          lookupGeometry(
            name);
    }
    private final Instance lookupInstance(String name) {
        return renderObjects.
          lookupInstance(
            name);
    }
    private final Camera lookupCamera(String name) {
        return renderObjects.
          lookupCamera(
            name);
    }
    private final Options lookupOptions(String name) {
        return renderObjects.
          lookupOptions(
            name);
    }
    public final Shader lookupShader(String name) {
        return renderObjects.
          lookupShader(
            name);
    }
    public final Modifier lookupModifier(String name) {
        return renderObjects.
          lookupModifier(
            name);
    }
    private final LightSource lookupLight(String name) {
        return renderObjects.
          lookupLight(
            name);
    }
    public final void shaderOverride(String name,
                                     boolean photonOverride) {
        scene.
          setShaderOverride(
            lookupShader(
              name),
            photonOverride);
    }
    public final void render(String optionsName,
                             Display display) {
        renderObjects.
          updateScene(
            scene);
        Options opt =
          lookupOptions(
            optionsName);
        if (opt ==
              null)
            opt =
              new Options(
                );
        scene.
          setCamera(
            lookupCamera(
              opt.
                getString(
                  "camera",
                  null)));
        String bakingInstanceName =
          opt.
          getString(
            "baking.instance",
            null);
        if (bakingInstanceName !=
              null) {
            Instance bakingInstance =
              lookupInstance(
                bakingInstanceName);
            if (bakingInstance ==
                  null) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to bake instance \"%s\" - not found",
                    bakingInstanceName);
                return;
            }
            scene.
              setBakingInstance(
                bakingInstance);
        }
        else
            scene.
              setBakingInstance(
                null);
        String samplerName =
          opt.
          getString(
            "sampler",
            "bucket");
        ImageSampler sampler =
          null;
        if (samplerName.
              equals(
                "none") ||
              samplerName.
              equals(
                "null"))
            sampler =
              null;
        else
            if (samplerName.
                  equals(
                    "bucket"))
                sampler =
                  bucketRenderer;
            else
                if (samplerName.
                      equals(
                        "ipr"))
                    sampler =
                      progressiveRenderer;
                else
                    if (samplerName.
                          equals(
                            "fast"))
                        sampler =
                          new SimpleRenderer(
                            );
                    else {
                        UI.
                          printError(
                            Module.
                              API,
                            "Unknown sampler type: %s - aborting",
                            samplerName);
                        return;
                    }
        scene.
          render(
            opt,
            sampler,
            display);
    }
    public final boolean parse(String filename) {
        if (filename ==
              null)
            return false;
        filename =
          includeSearchPath.
            resolvePath(
              filename);
        SceneParser parser =
          null;
        if (filename.
              endsWith(
                ".sc"))
            parser =
              new SCParser(
                );
        else
            if (filename.
                  endsWith(
                    ".ra2"))
                parser =
                  new RA2Parser(
                    );
            else
                if (filename.
                      endsWith(
                        ".ra3"))
                    parser =
                      new RA3Parser(
                        );
                else
                    if (filename.
                          endsWith(
                            ".tri"))
                        parser =
                          new TriParser(
                            );
                    else
                        if (filename.
                              endsWith(
                                ".rib"))
                            parser =
                              new ShaveRibParser(
                                );
                        else {
                            UI.
                              printError(
                                Module.
                                  API,
                                "Unable to find a suitable parser for: \"%s\"",
                                filename);
                            return false;
                        }
        String currentFolder =
          new File(
          filename).
          getAbsoluteFile(
            ).
          getParentFile(
            ).
          getAbsolutePath(
            );
        includeSearchPath.
          addSearchPath(
            currentFolder);
        textureSearchPath.
          addSearchPath(
            currentFolder);
        return parser.
          parse(
            filename,
            this);
    }
    public final BoundingBox getBounds() {
        return scene.
          getBounds(
            );
    }
    public void build() {  }
    public static SunflowAPI create(String filename,
                                    int frameNumber) {
        if (filename ==
              null)
            return new SunflowAPI(
              );
        SunflowAPI api =
          null;
        if (filename.
              endsWith(
                ".java")) {
            Timer t =
              new Timer(
              );
            UI.
              printInfo(
                Module.
                  API,
                "Compiling \"" +
                filename +
                "\" ...");
            t.
              start(
                );
            try {
                FileInputStream stream =
                  new FileInputStream(
                  filename);
                api =
                  (SunflowAPI)
                    ClassBodyEvaluator.
                    createFastClassBodyEvaluator(
                      new Scanner(
                        filename,
                        stream),
                      SunflowAPI.class,
                      ClassLoader.
                        getSystemClassLoader(
                          ));
                stream.
                  close(
                    );
            }
            catch (CompileException e) {
                UI.
                  printError(
                    Module.
                      API,
                    "Could not compile: \"%s\"",
                    filename);
                UI.
                  printError(
                    Module.
                      API,
                    "%s",
                    e.
                      getMessage(
                        ));
                return null;
            }
            catch (ParseException e) {
                UI.
                  printError(
                    Module.
                      API,
                    "Could not compile: \"%s\"",
                    filename);
                UI.
                  printError(
                    Module.
                      API,
                    "%s",
                    e.
                      getMessage(
                        ));
                return null;
            }
            catch (ScanException e) {
                UI.
                  printError(
                    Module.
                      API,
                    "Could not compile: \"%s\"",
                    filename);
                UI.
                  printError(
                    Module.
                      API,
                    "%s",
                    e.
                      getMessage(
                        ));
                return null;
            }
            catch (IOException e) {
                UI.
                  printError(
                    Module.
                      API,
                    "Could not compile: \"%s\"",
                    filename);
                UI.
                  printError(
                    Module.
                      API,
                    "%s",
                    e.
                      getMessage(
                        ));
                return null;
            }
            t.
              end(
                );
            UI.
              printInfo(
                Module.
                  API,
                "Compile time: " +
                t.
                  toString(
                    ));
            if (api !=
                  null) {
                String currentFolder =
                  new File(
                  filename).
                  getAbsoluteFile(
                    ).
                  getParentFile(
                    ).
                  getAbsolutePath(
                    );
                api.
                  includeSearchPath.
                  addSearchPath(
                    currentFolder);
                api.
                  textureSearchPath.
                  addSearchPath(
                    currentFolder);
            }
            UI.
              printInfo(
                Module.
                  API,
                "Build script running ...");
            t.
              start(
                );
            api.
              setCurrentFrame(
                frameNumber);
            api.
              build(
                );
            t.
              end(
                );
            UI.
              printInfo(
                Module.
                  API,
                "Build script time: %s",
                t.
                  toString(
                    ));
        }
        else {
            api =
              new SunflowAPI(
                );
            api =
              api.
                parse(
                  filename)
                ? api
                : null;
        }
        return api;
    }
    public static SunflowAPI compile(String code) {
        try {
            Timer t =
              new Timer(
              );
            t.
              start(
                );
            SunflowAPI api =
              (SunflowAPI)
                ClassBodyEvaluator.
                createFastClassBodyEvaluator(
                  new Scanner(
                    null,
                    new StringReader(
                      code)),
                  SunflowAPI.class,
                  (ClassLoader)
                    null);
            t.
              end(
                );
            UI.
              printInfo(
                Module.
                  API,
                "Compile time: %s",
                t.
                  toString(
                    ));
            return api;
        }
        catch (CompileException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage(
                    ));
            return null;
        }
        catch (ParseException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage(
                    ));
            return null;
        }
        catch (ScanException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage(
                    ));
            return null;
        }
        catch (IOException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage(
                    ));
            return null;
        }
    }
    public int getCurrentFrame() { return currentFrame;
    }
    public void setCurrentFrame(int currentFrame) {
        this.
          currentFrame =
          currentFrame;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "1OWr+T88eIJsmjgAAA==");
}
