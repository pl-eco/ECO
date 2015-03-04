package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class PinholeLens implements CameraLens {
    private float au;
    private float av;
    private float aspect;
    private float fov;
    public PinholeLens() { super();
                           fov = 90;
                           aspect = 1;
                           update(); }
    public boolean update(ParameterList pl, SunflowAPI api) { fov = pl.getFloat(
                                                                         "fov",
                                                                         fov);
                                                              aspect = pl.
                                                                         getFloat(
                                                                           "aspect",
                                                                           aspect);
                                                              update(
                                                                );
                                                              return true;
    }
    private void update() { au = (float) Math.tan(Math.toRadians(
                                                         fov *
                                                           0.5F));
                            av = au / aspect; }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight,
                      double lensX,
                      double lensY,
                      double time) { float du = -au + 2.0F *
                                       au *
                                       x /
                                       (imageWidth -
                                          1.0F);
                                     float dv = -av + 2.0F *
                                       av *
                                       y /
                                       (imageHeight -
                                          1.0F);
                                     return new Ray(0, 0,
                                                    0,
                                                    du,
                                                    dv,
                                                    -1); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcxRWfW/852zG5i5M4wQQ7cRzUJPS2qUql1AianGzi" +
                                                    "9MCnOKTCCJzx7py9yd7OZnfOvjg1hUhVUlSlFRgaKuoPVRCFBoKqRrSqkPKl" +
                                                    "BUS/UFWt+qFQ9UtRaT7kQykqbel7M7u3e3t3pkitpZ2bnXnvzXvz3vu9t758" +
                                                    "nXT4Htnrcvv0nM1FjlVF7oR9R06cdpmfO1y4o0g9n5l5m/r+UVibMYZfyXzw" +
                                                    "0XfmsxrpnCYbqeNwQYXFHf8I87m9wMwCyUSrYzYr+4JkCyfoAtUrwrL1guWL" +
                                                    "0QJZF2MVZKQQqqCDCjqooEsV9AMRFTDdxJxKOY8c1BH+KfIISRVIp2ugeoLs" +
                                                    "qBfiUo+WAzFFaQFI6ML3Y2CUZK56ZHvNdmVzg8FP7dVXvvtw9sdtJDNNMpYz" +
                                                    "heoYoISAQ6ZJb5mVZ5nnHzBNZk6TDQ5j5hTzLGpbS1LvadLnW3MOFRWP1S4J" +
                                                    "Fysu8+SZ0c31GmibVzEE92rmlSxmm+FbR8mmc2Brf2SrsnAc18HAHgsU80rU" +
                                                    "YCFL+0nLMQUZSnLUbBz5ChAAa7rMxDyvHdXuUFggfcp3NnXm9CnhWc4ckHbw" +
                                                    "CpwiyEBLoXjXLjVO0jk2I8jWJF1RbQFVt7wIZBFkc5JMSgIvDSS8FPPP9fvu" +
                                                    "vHDGOeRoUmeTGTbq3wVMgwmmI6zEPOYYTDH27ik8TftfO68RAsSbE8SK5tWv" +
                                                    "3fjy7YPX3lA0tzShmZw9wQwxY1yaXf/2tvzu/W2oRpfLfQudX2e5DP9isDNa" +
                                                    "dSHz+msScTMXbl478ssHHn2Rva+RngnSaXC7UoY42mDwsmvZzLuHOcyjgpkT" +
                                                    "pJs5Zl7uT5A0zAuWw9TqZKnkMzFB2m251MnlO1xRCUTgFaVhbjklHs5dKubl" +
                                                    "vOoSQtLwkBw8XUT9yV9BvqrP8zLTqUEdy+E6xC6jnjGvM4PrPi27NnjNrzgl" +
                                                    "my/qvmfo3JurvRvcY7oBUeVRvWg589xmBeb4OQww9/8nuopWZRdTKbjwbcl0" +
                                                    "tyFTDnHbZN6MsVI5OHbj5Zm3tFr4B/chyE44LBcclsPDcuqwXOwwkkrJMzbh" +
                                                    "ocqh4I6TkNgAeb27px46fPz8cBtEkrvYDneJpMNgW6DJmMHzUfZPSIwzIAS3" +
                                                    "/uDBc7kPn79bhaDeGqqbcpNrFxcfO/b1z2lEq8dctAyWepC9iEhZQ8SRZK41" +
                                                    "k5s5994HV55e5lHW1YF4AAaNnJjMw0kfeNxgJsBjJH7Pdnp15rXlEY20A0IA" +
                                                    "KgoKUQyAM5g8oy6pR0OARFs6wOAS98rUxq0Q1XrEvMcXoxUZHOvlfAM4ZR1G" +
                                                    "eT88vUHYy1/c3ejiuEkFE3o5YYUE4PGfXXvm6vf27tfiWJ2JVb8pJlTmb4iC" +
                                                    "5KjHGKz/4WLxyaeun3tQRghQ7Gx2wAiOecABcBlc6zfeOPX7d9+59BstiioB" +
                                                    "BbEya1tGFWTcFp0CKGEDUqHvR+53yty0ShadhciF4PxnZte+q3+9kFXetGEl" +
                                                    "DIbbP1lAtH7zQfLoWw//fVCKSRlYpSLLIzJ1ARsjyQc8j55GPaqP/frWZ16n" +
                                                    "3wcQBeDyrSUmsYhIy4i8el26ao8cc4m9fThsdxv2qnJlq3xrh6N3t06icSy2" +
                                                    "seT7x6Q9e/ZPH0qLGtKnSY1J8E/rl58dyN/1vuSP4hi5h6qNaASNScT7+RfL" +
                                                    "f9OGO3+hkfQ0yRpB13OM2hWMlmmo9H7YCkFnVLdfX7VViRqt5em2ZA7Fjk1m" +
                                                    "UISCMEdqnPckkkbmyM3wdAdJ051MmhSRk/2SZViOu3D4TBizadezFii2VESj" +
                                                    "lbV9VPSsMpTJhaCO68t975589r2XFEAmHZIgZudXHv84d2FFi3VGOxuakziP" +
                                                    "6o6kxTcpiz+GvxQ8/8YHLcUFVR378kGJ3l6r0a6LibhjLbXkEeN/vrL88x8u" +
                                                    "n1Nm9NU3BmPQ977023/9Knfxj282qU7gMk5lzmZV9H/h0/tmAodRvP4FnB1s" +
                                                    "LW0Qnp5AWk8LaYVAWif1XQi+/4HEyUBiW4nHFZSOGYkleErONwtyS0PJzsuS" +
                                                    "jaUaXXJrq55SuuPS2ZVVc/K5fVqALeOCdAvuftZmC8yOHdeGkuqq+L2yi47y" +
                                                    "+PEXfvSqeHvvl5Rj97SO6yTj62f/MnD0rvnjn6J2DyVsSop84d7Lb95zm/GE" +
                                                    "RtpqcNDwYVDPNFoPAj0egy8Z52gdFAzW3LkxDLe+wJ19Tetn5LQIyTV5n1ro" +
                                                    "vsEG90lTGXx3YKkIyfrjZFPq90BxQh5zfI1aUcLhIQjQimtCmkqau3HIq2ox" +
                                                    "Bpg0y6Gzo05jQZELDzTm2EBg9EBLo0fXUImvsXcKB3tNddsXuGV+oq4ZXNwM" +
                                                    "z1Cg69B/7aC0lJiuJd/BZlq0wfcnTs802+w0ObQmMnAekYM8bGkNu8/isAic" +
                                                    "c0wcoadDr29qCA7YbFL4BVkXa9Cx7dja8L2vvlGNl1czXVtW7/+dbDlr35Hd" +
                                                    "8DFXqth2vAzG5p2ux0qW1LRbFUUFSN8UZEuLLwawRU2ktucV/bcEySbpwaP4" +
                                                    "Eyf7NtgTI4MQDWZxoifACUCE0yfd8MKystvCdiCn2oEqiYEYNpzxt7ruE3FK" +
                                                    "/i8lxJSK+m/KjHFl9fB9Z2588TkJUB2GTZeWUEpXgaRV413DpR0tpYWyOg/t" +
                                                    "/mj9K927Qrxdj0Nf0G0ndBtq3pSOlV0h28iln275yZ3Pr74ju+L/ADn041Tk" +
                                                    "EgAA");
}
