package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public final class GIEngineFactory {
    public static final GIEngine create(Options options) { String type = options.
                                                             getString(
                                                               "gi.engine",
                                                               null);
                                                           if (type ==
                                                                 null ||
                                                                 type.
                                                                 equals(
                                                                   "null") ||
                                                                 type.
                                                                 equals(
                                                                   "none"))
                                                               return null;
                                                           else
                                                               if (type.
                                                                     equals(
                                                                       "ambocc"))
                                                                   return new AmbientOcclusionGIEngine(
                                                                     options);
                                                               else
                                                                   if (type.
                                                                         equals(
                                                                           "fake"))
                                                                       return new FakeGIEngine(
                                                                         options);
                                                                   else
                                                                       if (type.
                                                                             equals(
                                                                               "igi"))
                                                                           return new InstantGI(
                                                                             options);
                                                                       else
                                                                           if (type.
                                                                                 equals(
                                                                                   "irr-cache"))
                                                                               return new IrradianceCacheGIEngine(
                                                                                 options);
                                                                           else
                                                                               if (type.
                                                                                     equals(
                                                                                       "path"))
                                                                                   return new PathTracingGIEngine(
                                                                                     options);
                                                                               else {
                                                                                   UI.
                                                                                     printWarning(
                                                                                       Module.
                                                                                         LIGHT,
                                                                                       "Unrecognized GI engine type \"%s\" - ignoring",
                                                                                       type);
                                                                                   return null;
                                                                               }
    }
    public GIEngineFactory() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1166659882000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "9qUtLnFv8vMFn226PPVQwvjf6I0ze4YNAAA=");
}
