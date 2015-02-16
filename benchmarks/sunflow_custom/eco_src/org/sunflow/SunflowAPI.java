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
    final public static String VERSION = "0.07.2";
    final public static String DEFAULT_OPTIONS = "::options";
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
                                          long maxMb = Runtime.getRuntime().
                                            maxMemory() /
                                            1048576;
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
                          this.reset(); }
    final public void reset() { scene = new Scene(
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
    final public String getUniqueName(String prefix) {
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
    final public void parameter(String name,
                                String value) {
        parameterList.
          addString(
            name,
            value);
    }
    final public void parameter(String name,
                                boolean value) {
        parameterList.
          addBoolean(
            name,
            value);
    }
    final public void parameter(String name,
                                int value) {
        parameterList.
          addInteger(
            name,
            value);
    }
    final public void parameter(String name,
                                float value) {
        parameterList.
          addFloat(
            name,
            value);
    }
    final public void parameter(String name,
                                Color value) {
        parameterList.
          addColor(
            name,
            value);
    }
    final public void parameter(String name,
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
    final public void parameter(String name,
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
    final public void parameter(String name,
                                Matrix4 value) {
        parameterList.
          addMatrices(
            name,
            InterpolationType.
              NONE,
            value.
              asRowMajor());
    }
    final public void parameter(String name,
                                int[] value) {
        parameterList.
          addIntegerArray(
            name,
            value);
    }
    final public void parameter(String name,
                                String[] value) {
        parameterList.
          addStringArray(
            name,
            value);
    }
    final public void parameter(String name,
                                String type,
                                String interpolation,
                                float[] data) {
        InterpolationType interp;
        try {
            interp =
              InterpolationType.
                valueOf(
                  interpolation.
                    toUpperCase());
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
    final public void addIncludeSearchPath(String path) {
        includeSearchPath.
          addSearchPath(
            path);
    }
    final public void addTextureSearchPath(String path) {
        textureSearchPath.
          addSearchPath(
            path);
    }
    final public String resolveTextureFilename(String filename) {
        return textureSearchPath.
          resolvePath(
            filename);
    }
    final public String resolveIncludeFilename(String filename) {
        return includeSearchPath.
          resolvePath(
            filename);
    }
    final public void shader(String name,
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
        if (this.
              lookupShader(
                name) !=
              null)
            this.
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
    final public void modifier(String name,
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
        if (this.
              lookupModifier(
                name) !=
              null)
            this.
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
    final public void geometry(String name,
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
        if (this.
              lookupGeometry(
                name) !=
              null)
            this.
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
    final public void geometry(String name,
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
        if (this.
              lookupGeometry(
                name) !=
              null)
            this.
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
    final public void instance(String name,
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
            this.
              parameter(
                "geometry",
                geoname);
            renderObjects.
              put(
                name,
                new Instance(
                  ));
        }
        if (this.
              lookupInstance(
                name) !=
              null)
            this.
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
    final public void light(String name, LightSource light) {
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
        if (this.
              lookupLight(
                name) !=
              null)
            this.
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
    final public void camera(String name,
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
        if (this.
              lookupCamera(
                name) !=
              null)
            this.
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
    final public void options(String name) {
        if (this.
              lookupOptions(
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
        assert this.
          lookupOptions(
            name) !=
          null;
        this.
          update(
            name);
    }
    final public Geometry lookupGeometry(String name) {
        return renderObjects.
          lookupGeometry(
            name);
    }
    final private Instance lookupInstance(String name) {
        return renderObjects.
          lookupInstance(
            name);
    }
    final private Camera lookupCamera(String name) {
        return renderObjects.
          lookupCamera(
            name);
    }
    final private Options lookupOptions(String name) {
        return renderObjects.
          lookupOptions(
            name);
    }
    final public Shader lookupShader(String name) {
        return renderObjects.
          lookupShader(
            name);
    }
    final public Modifier lookupModifier(String name) {
        return renderObjects.
          lookupModifier(
            name);
    }
    final private LightSource lookupLight(String name) {
        return renderObjects.
          lookupLight(
            name);
    }
    final public void shaderOverride(String name,
                                     boolean photonOverride) {
        scene.
          setShaderOverride(
            this.
              lookupShader(
                name),
            photonOverride);
    }
    final public void render(String optionsName,
                             Display display) {
        renderObjects.
          updateScene(
            scene);
        Options opt =
          this.
          lookupOptions(
            optionsName);
        if (opt ==
              null)
            opt =
              new Options(
                );
        scene.
          setCamera(
            this.
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
              this.
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
    final public boolean parse(String filename) {
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
          getAbsoluteFile().
          getParentFile().
          getAbsolutePath();
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
    final public BoundingBox getBounds() {
        return scene.
          getBounds();
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
              start();
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
                        getSystemClassLoader());
                stream.
                  close();
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
                      getMessage());
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
                      getMessage());
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
                      getMessage());
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
                      getMessage());
                return null;
            }
            t.
              end();
            UI.
              printInfo(
                Module.
                  API,
                "Compile time: " +
                t.
                  toString());
            if (api !=
                  null) {
                String currentFolder =
                  new File(
                  filename).
                  getAbsoluteFile().
                  getParentFile().
                  getAbsolutePath();
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
              start();
            api.
              setCurrentFrame(
                frameNumber);
            api.
              build();
            t.
              end();
            UI.
              printInfo(
                Module.
                  API,
                "Build script time: %s",
                t.
                  toString());
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
              start();
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
              end();
            UI.
              printInfo(
                Module.
                  API,
                "Compile time: %s",
                t.
                  toString());
            return api;
        }
        catch (CompileException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage());
            return null;
        }
        catch (ParseException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage());
            return null;
        }
        catch (ScanException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage());
            return null;
        }
        catch (IOException e) {
            UI.
              printError(
                Module.
                  API,
                "%s",
                e.
                  getMessage());
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170572658000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAMVcC3QcVRm+u5s0zaMkfbf0Xfqgr90+ktImlTbkUUK3SUjS" +
       "QIs1TGZvNkNnZ4aZ\n2XRTKqKcQxF8tIBHPVIeiqcP5aFVqwesICBC9Yio5Q" +
       "iC9FRFj6ByFAXliP9/5+7O7OzuNOmUtefM\nv5OZe+/833f//7//vTO3X3+T" +
       "lBo6mSkaYXNYo0a4qbtT0A0aa5IFw+iBS33i06XlnYe2KGqQBKIk\nKMVMUh" +
       "0VjUhMMIWIFIu0NTekdLJMU+XhuKyaYZoyw9fJdby9K6J1OQ1edc/xCR9/oG" +
       "ROkJRGSbWg\nKKopmJKqtMg0YZikJnqdMCREkqYkR6KSYTZEyTiqJBNNqmKY" +
       "gmIa15MbSShKxmgitmmSedH0wyPw\n8Igm6EIiwh4f6WSPhRYm6tQUJIXGGj" +
       "OPg5rLs2uC2rxeV25paGQs3uwFOEwDQD03g9pCmwNVCx3u\nXbv3viMhUr2D" +
       "VEtKNzYmAhITnreDVCVoop/qRmMsRmM7yHiF0lg31SVBlvawp+4gEwwprghm" +
       "UqdG\nFzVUeQgLTjCSGtXZM9MXo6RKREx6UjRVPcPRgETlWPqv0gFZiAPsKT" +
       "ZsC24rXgeAFRIopg8IIk1X\nKdklKdDjc9w1MhgXbIECULUsQc1BNfOoEkWA" +
       "C2SC1ZeyoMQj3aYuKXEoWqom4SkmubBgo8i1Joi7\nhDjtM8k0d7lO6xaUKm" +
       "dEYBWTTHYXYy1BL13o6iVH/yyb8s9bD999YhOz7ZIYFWXUvwIqzXZV6qID\n" +
       "VKeKSK2K7yTDd7VtT84MEgKFJ7sKW2UaFx7fFv3jD+ZYZWbkKdPRfx0VzT6x" +
       "/cCcrhs2qySEaozV\nVEPCzs9Cztyhk99pSGngtVMyLeLNcPrm410/2n7TUf" +
       "rnIKloI2NEVU4mwI7Gi2pCk2Sqb6YK1QWT\nxtpIOVViTex+GymD8yiYvHW1" +
       "Y2DAoGYbKZHZpTEq+xsoGoAmkKJyOJeUATV9rgnmIDtPaYSQMjhI\nFRxrif" +
       "WP/ZpkSThiJJUBWd0dMXQxourxzN/d1m9jZ1sYTUYzSUtkUE3QiCAKiqSokb" +
       "gETiqqK2J0\nCH9H2lAKtZqwOxDAMOd2Vxks/XJVjlG9Tzx05rm9LVs+eatl" +
       "Cmi+HA/4C7Qf5u2H7fZJIMCanYTP\nsfoAGNwFvghRq2pJ984rrr11fgg6X9" +
       "tdAvCx6HzQnD+8RVSbbIdtY7FNBKuZ9uVr9oXfObTRsppI\n4biat3bl8w+e" +
       "vO/vVUuDJJg/6CEoCLsV2EwnRspMMFvgdpN87f/ltq3HTp185WLbYUyyIMeP" +
       "c2ui\nH85306+rIo1BZLObf2B6degq0nsgSErAuSGgMf0hVsx2PyPLHxvSsQ" +
       "2xlEVJ5YCqJwQZb6UDUoU5\nqKu77SvMLmrY+UTonEo00MlwbOcWy37x7mQN" +
       "5RTLjrC3XShY7Hzn5jErX3ys8mlGSzrMVjsGsm5q\nWk473jaWHp1SuP7KFz" +
       "rv/Nyb+65hlsJNxYTRLdkvS2IKqiyyq4C3yhAxsCMXbFMSakwakIR+maLF\n" +
       "vVe9cNW33/hMjdU1MlxJ9+zyszdgX59+Gbnp5Ef+NZs1ExBxtLBh2MUsNBPt" +
       "lht1XRhGPVIff2HW\nF58RDkIwgwBiSHsoiwmEISOMxwjjfSmTYde9VSjmQ9" +
       "vLC5h+nrG5T9x7ND4/ef2z32NaVwrOQd7Z\nDVsFrcHqeRQXIbtT3d57uWAM" +
       "Qrnax9s/XCM//h9ocQe0KMKYaHToECpSWZ3IS5eWvfTEk1Ou/XmI\nBFtJha" +
       "wKsVaB2T8pB8OjxiBEmZS2cROzrZrdY1EyyISRcCEnIOX4qwKUW1LY/VtxZL" +
       "c9p69/+eHo\ncx0HGQEFHT/PwOZqZ8+Jbfe881PzVdaO7YFYe14qN4RCNmTX" +
       "XXdqaPyYR+5NBEnZDlIj8nytV5CT\naOc7IL0w0kkc5HRZ97NTBWtcbMhEmJ" +
       "lu73c81u37duiGcyyN51Uud5+GbK+G4xLu7pe43T2APrgy\nvPKS8Gq8sJFV" +
       "XcDkYstJQyY8WFIEljIsgdIGS+5SJinrbenqbutoZ6WnQkbLzAWRha0kyIol" +
       "KFnj\nm6xurytoHvXZin8IjnVc8XX5FC+vr1e1TK9vyaM7njczxVG0gNLVzS" +
       "2tjduiPX0dnT2ge/dIlY+O\nXHnMCBiCeq58fY7yhJ1sy9UYUJVpujQkYOYM" +
       "kxYR0pi0ilnjs6jqNNyNt12K9o5S0aVwNHBFGwoo\n2ofiKpNc0J8Ud1GzC1" +
       "InGJX0tGIX5yim8xLhy7IquFS9dpSq1sKxgau6oYCqA1zViZquxiEiGdIQ\n" +
       "deu7orC+nbm1XErHR6n0Em7JaYvOp3SCKz1eUkQ5GYMwIujiYCckm2mVZzlV" +
       "NoYNkybCdimXiso5\nqHgpV/HSAiqaaRXRMCGZ8alicpQqXgzHRq7ixgIq3s" +
       "BVHMemxhQmXzhOp9WbndPpnc5iLv32noMX\nbeL6bSqg3yfS+lnWZkV/I63f" +
       "DKd+XY4SMOy6lLt5lMrNgqORK9dYQLlPcuWqxKQO+pmtyA0MzdOc\naya6lI" +
       "C51xBLRs/cMv/7P9527z4rgfcYwbNq9Ykf++1ru0KffaLfqucepl2FD8x+4A" +
       "/HznRNspI9\na5J+Uc482VnHmqizcbBaw8RnntcTWOmnls37+o1dr3KNJmRP" +
       "N1uUZOL14Sfp4g2fPp1nzhSSFLfp\n3ObRO0yvRVm5EKg4q9DMnam37+q3qm" +
       "4RntoZ5JnjHTDwmaq2QqZDVHY0tRxbypp4bWVrFXbictuR\nrx03f76s3gK6" +
       "tHCXuSsurb9vz5z6h28/h+nWHBc2d9Pjh2ZcGRqUfhxkyylWHpSzDJNdqSE7" +
       "+6kA\nfZK60pOVA83NeMAEZHkxHJu5B2x2e4Ddf1lDcdCRN3gk8l/1uHcIxX" +
       "0wbupJpZtFxKZBKu5iZT+l\nWa0eMEnJkCrFbAO6/2zunTEd/OPuDNLxaV8X" +
       "OFJhNEitNMkL6bc87n0HxcOQscDgSZlHHLEBPeIH\nEA76SQ4o6R9QgC9S8K" +
       "ibk/axJh/3QPoUikchisepuU2Rrk/SdoyUhdqzWXjMDwuYQu7jLOzzz0LQ\n" +
       "KuCtdUF6nveg5xSKkxCiMoOwyxh+4ocGTFDv4DTcUSQaHI5a1q+qMhUU9qjX" +
       "PFh4HcXLHiz8xg8L\nOIs7yFk4+MGzgNf3s3bf8oD8Noo3PCC/6QfyejgOc8" +
       "iHi9/xMPVWBZM96P3CHATG4MV/e3DwH78c\nHOMcHCteDJjqTEylhBCnuLym" +
       "6gxxjQcbU1BUFmYjUOU3Ip7gbJz4P7GRgIlNuFOFHHANQzzXg41F\nKGZ4sD" +
       "HTb2B8jrPxXPHYmJbDRi/F9UCLjpUedKxFscyDjuV+6TjF6Tj1/6RjqwAFUr" +
       "UM8iYPOlpR\nNHjQscFv5DjN6ThdBDp0MskxbWRL5pieH9nfPLFr/TU3s2lE" +
       "uSBLgtFu5/FBKYZnAZg0LCw8Nck0\n1icu3nn8b0+coIvZ0vVYyegV9EY9nu" +
       "dNqKPOW8JRuvXFgXvYm5eSfsGw5gzuV8i5b4izXvwyzi7Q\n2M9+TdMIpAYM" +
       "Tm0tYJ8A2PGzgbAUC0dVUZDbmu9/ovKFA8m1V1gzr3GOAm3Nex+5omrs/bff" +
       "wmZA\nnIRyx/tR/nfZkKC321NPtAmYrnecl7eH9atX1y2vrVtRtxaGuiFcqG" +
       "ZG2elhsB9BscXDYM+6YOpl\nsLi69wY32DeK47+B7owKK/CJa7xUKNiSZQwV" +
       "zBhW1a1cuYY1fTUK9fz11pq1rLfWZ/XWoEdv4YwM\nX3IV7K2Yn97aCcd7nK" +
       "r3/PdWCStQctZoO7obgW7mr4Fglr/W2f1zy/nrn7rVy9etXLF+NUzt0c/Z\n" +
       "c2/06J7bUQx7dM+ec+we9r73IpjvTrN6x/rN6R4Ui1z6jWSiHLjTA9TnUew3" +
       "yRidJtQh6kJ0wA+i\nRaDYQo5o4flFdK8Hoq+guBsQJTXoVobojI3ooM8pXW" +
       "AVR7TKvwuNCOqDHlC/geKwSSYJsVib+6WE\nqyuP+AW+ngNfXyTgj3oA/wGK" +
       "71jAe9yvOlzAj/sBvhHUbOHAW4oE/FkP4D9F8ZRJpujWp3YcfKsk\nU2VEC1" +
       "yBp/3ScSWn48oi0fFrDzpeQfFLmw7uBKOg41d+6FgHyu/kdOz0T8c5zW6tV8" +
       "yDAn4MgoD+\n5EHXX1H8Dr8OYOVdjvJ7P1RcChSkOBWp4lExPYeKrewzIk7G" +
       "ux5kvI/iHyYZm+A1XHS87YcOiBWB\nuzgddxWPjjyvT9Mv0tjXUFAsWF6Yk2" +
       "A1ihLgJE4hxTL14WxOgqV+OGkFLh7inDxUPE5m5XDSQw2D\nypDy9cssKQ9O" +
       "96BkDopJhSmZ7HMFJPAsp+TZ4lGSN9AGl3iwEEGxAFiQ+Os9FwsL/bLAFz4C" +
       "xVj4\n4Ddm5hhGVIoPmt1qUhctu1jvwchGFLUwt5OxkouOOp9rpgE+UQuch4" +
       "naSOmYkUNHEwyhuhClisEQ\nt3mw0YGiGUYWkdVx0dHih46loPxkiw7r1x8d" +
       "I8k5gts9oO5E0QNzU8dXbQ6s2/xgrQPVl3CsS4qE\nNe6BdReKfpNcIKvqrq" +
       "S2mYfAwqNvVgnGh3iOfLAvAtYCvFrOR+2o+dh+Tnzs9uADP14K6hk+0t86\n" +
       "FOYjqwTjw/DDx2qAxz+wCuZ8YPUB8bHPgw9cEgl+wiRVFh9WwEg3l5umOu4z" +
       "Ls76iZQXF2APwSbO\nRVORuPi8BxdfQnGHScZZXHRY0SHd3rQcMpwFGBt3+o" +
       "kcqwBcO2ejvUiR45AHG19D8eWMZVgTlMKW\n4bjPuPiK3yjKt04EC2ydOO9c" +
       "HPfg4lEU38xEjfQMpXDUyCrB+DjmN4rGOB+xInnKMx58nETxQ5NU\nWnywrG" +
       "tESZnNx5N+7KMN4GmcD82/fYzky4wzDPiLHqS8jOIFMBJrbt4xRHVdirnz7F" +
       "/4wd0IWu7l\nuPd+8LgLh75mydBkYZjh/r0HJ39G8RpbnFZy1iuCp/1wEQHN" +
       "j3IujhYpRrztAfVdFH+D6YSG23aZ\nydhI3/KD9GJQ/BmO9Bn/SB1Kh0Ie90" +
       "pR8f+apDxOzcvUpBLLDIUzc96/s/vA0mVqygb9vp93D1MB\nw8846J/lBY3C" +
       "/e7BqX+1x73xKKqgs/qTkhzLNsvQOD+hugX0fZnr/fJoOiv/J7Aj/mgsNMMD" +
       "Li6B\nhKbg5E6ngkmNj6Kpk+mOjwfaVYVt5ZVEfIGfSm9kY0/S6QBu08NNG6" +
       "nh04tfmvuTmqaT1oa+QZMs\ndOy04iUjbcqQKrJPli8XlJhMdWt/38y8D7xK" +
       "FzSN6s+/9oedn132+o/w3b1md8ZUP50BOVWIzzxD\no5p55u+MkcSIkMduxN" +
       "AaFEth5sm3T2Oh0DwmF9qQl/nxm7kAdQ2HvOac/OZDHvdwzSRUb5JqiAlN\n" +
       "jh0MzAxtBA1+EGCn8W0yoZxtMgUR8L6x/WGzB4w2FM0Aw8iF4QgEI171SJmk" +
       "wn47jaY+Lef/crD+\n/wEx+tINH/5n9FfvWl/GpP+PgMooGTuQlGXnbkPH+R" +
       "gNPEti4CuZrGEv2UOdkII5YjEYFj9jJtVh\nFeo2SQgK4WmPlsd2re0vqSxA" +
       "COGirK+D2Cvz9OaCpPU/YfSJVz94zdzU7T372adGpaIs7NmDzVRE\nSZm1aZ" +
       "q1ihsU5hVsLd3W8+SRh3sfe2h9egMG21Q7yeGmWUaz2rrr0UE6mZN/p3JLQj" +
       "PZ3uI93536\nrQ2H7nmVhRztfxuOoBq+RAAA");
}
