ECO
===

ECO compiler impelementation

This project implements Eco compiler, a novel energy-aware programming model centering around sustainability. 
A sustainable program adaptively adjusts its own behaviors to stay on a given energy budget, avoiding both deficit
that would lead to battery drain or CPU overheating, and surplus that could have been used to improve the quality 
of the program output. Sustainability management in Eco is built on a key insight where sustainability is viewed 
as a form of supply and demand matching, and a sustainable program consistently maintains the equilibrium between supply 
and demand. Concretely, Eco introduces a novel language abstraction-sustainable blocks-to achieve fine-grained programmable 
sustainability. Eco is implemented as a minimal extension to Java, and we validate its design by upgrading real-world Java
programs, enabling two crucial features toward energy-aware software: battery awareness and temperature awareness.

Quickstart
===

Run ant from the source directory. Type ```ant jar``` to build jars of the eco compiler and the associated runtime. Add /bin to your path to run ecoc and eco. Included is a jar of polyglot to build and run eco (modification of polyglot 2.6.1).

ecoc runs similar to the javac compiler, 

```ecoc -d /path_to_output Hello.eco```

Larger projects can be build by combining eco with ant by modifing build.xml to point to the eco compiler instead of the javac compiler. The following is a quick example.

```
<!-- build directory targets -->
<target name="compile" depends="init" description="Compile source">
  <mkdir dir="${build.classes.dir}" />
  <javac 
    fork="yes"
    executable="/path_to_eco/bin/ecoc"
    srcdir="${src.dir}" 
    destdir="${build.classes.dir}" 
    source="${sunflow.jdk.level}" 
    classpath="${sunflow.libs}" />
</target>

```

A compiled ecoc program can be invoked using the eco script found in /bin. This will link in the necessary eco-rt.jar.

```
eco -cp /path_to_classes Hello
```

Samples
===
/samples contains full programs that compile and run with eco. Currently, a modified sunflow is available for use. The following sequence of commands will build and run the sunflow benchmark that demonstrates the sustainable programming model of eco.

```
ant compile;
eco -cp build/classes:janino.jar SunflowGUI -rtbench
```

See RealtimeBenchmark.java (src/org/sunflow/RealtimeBenchmark.java) for the main sustainable loop. This can be modified test various supplies (both battery and temperature).

```
sustainable {
  while (phi < totalPhi) {

  eye.x = 30 * (float) Math.cos(phi);
  eye.y = 30 * (float) Math.sin(phi);
  phi += Math.PI / 30; 
  frames++;
  // update camera
  parameter("eye", eye);
  parameter("target", target);
  parameter("up", up);
  camera(name, null);
  render(SunflowAPI.DEFAULT_OPTIONS, display);
  }   
}   
bsupply(battery * 0.1)
demand(totalPhi) -> (totalPhi - phi);
```

Tech Report
===
Tech report can be found [here](https://github.com/pl-eco/ECO/blob/master/tech.pdf)
