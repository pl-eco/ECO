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
      1170572658000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "EWyKOAAA");
}
