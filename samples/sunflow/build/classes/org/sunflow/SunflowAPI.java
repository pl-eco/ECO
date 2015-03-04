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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
