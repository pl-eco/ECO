package org.sunflow.system;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
@SuppressWarnings("serial") 
public class RenderGlobalsPanel extends JTabbedPane {
    private JPanel generalPanel;
    private javax.swing.JComboBox maxSamplingComboxBox;
    private JPanel samplingPanel;
    private javax.swing.JComboBox minSamplingComboBox;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JRadioButton defaultRendererRadioButton;
    private JRadioButton bucketRendererRadioButton;
    private JPanel bucketRendererPanel;
    private JLabel jLabel2;
    private JPanel rendererPanel;
    private JTextField threadTextField;
    private JCheckBox threadCheckBox;
    private JLabel jLabel3;
    private JPanel threadsPanel;
    private JLabel jLabel1;
    private JPanel resolutionPanel;
    private JTextField resolutionYTextField;
    private JTextField resolutionXTextField;
    private JCheckBox resolutionCheckBox;
    private void initialize() {  }
    public static void main(String[] args) { JFrame frame = new JFrame();
                                             frame.getContentPane().add(new RenderGlobalsPanel(
                                                                          ));
                                             frame.setDefaultCloseOperation(
                                                     WindowConstants.
                                                       DISPOSE_ON_CLOSE);
                                             frame.pack();
                                             frame.setVisible(true);
    }
    public RenderGlobalsPanel() { super();
                                  initialize();
                                  initGUI(); }
    private void initGUI() { try { setPreferredSize(new Dimension(
                                                      400,
                                                      300));
                                   { generalPanel = new JPanel();
                                     FlowLayout generalPanelLayout =
                                       new FlowLayout(
                                       );
                                     generalPanelLayout.setAlignment(
                                                          FlowLayout.
                                                            LEFT);
                                     generalPanel.setLayout(generalPanelLayout);
                                     this.addTab("General", null,
                                                 generalPanel,
                                                 null);
                                     { resolutionPanel = new JPanel(
                                                           );
                                       generalPanel.add(resolutionPanel);
                                       FlowLayout resolutionPanelLayout =
                                         new FlowLayout(
                                         );
                                       resolutionPanel.setLayout(
                                                         resolutionPanelLayout);
                                       resolutionPanel.setBorder(
                                                         BorderFactory.
                                                           createTitledBorder(
                                                             BorderFactory.
                                                               createEtchedBorder(
                                                                 BevelBorder.
                                                                   LOWERED),
                                                             "Resolution",
                                                             TitledBorder.
                                                               LEADING,
                                                             TitledBorder.
                                                               TOP));
                                       { resolutionCheckBox =
                                           new JCheckBox(
                                             );
                                         resolutionPanel.
                                           add(
                                             resolutionCheckBox);
                                         resolutionCheckBox.
                                           setText(
                                             "Override");
                                       }
                                       { jLabel1 = new JLabel(
                                                     );
                                         resolutionPanel.
                                           add(
                                             jLabel1);
                                         jLabel1.setText(
                                                   "Image Width:");
                                       }
                                       { resolutionXTextField =
                                           new JTextField(
                                             );
                                         resolutionPanel.
                                           add(
                                             resolutionXTextField);
                                         resolutionXTextField.
                                           setText(
                                             "640");
                                         resolutionXTextField.
                                           setPreferredSize(
                                             new Dimension(
                                               50,
                                               20)); }
                                       { jLabel2 = new JLabel(
                                                     );
                                         resolutionPanel.
                                           add(
                                             jLabel2);
                                         jLabel2.setText(
                                                   "Image Height:");
                                       }
                                       { resolutionYTextField =
                                           new JTextField(
                                             );
                                         resolutionPanel.
                                           add(
                                             resolutionYTextField);
                                         resolutionYTextField.
                                           setText(
                                             "480");
                                         resolutionYTextField.
                                           setPreferredSize(
                                             new Dimension(
                                               50,
                                               20)); } }
                                     { threadsPanel = new JPanel(
                                                        );
                                       generalPanel.add(threadsPanel);
                                       threadsPanel.setBorder(
                                                      BorderFactory.
                                                        createTitledBorder(
                                                          BorderFactory.
                                                            createEtchedBorder(
                                                              BevelBorder.
                                                                LOWERED),
                                                          "Threads",
                                                          TitledBorder.
                                                            LEADING,
                                                          TitledBorder.
                                                            TOP));
                                       { threadCheckBox =
                                           new JCheckBox(
                                             );
                                         threadsPanel.add(
                                                        threadCheckBox);
                                         threadCheckBox.setText(
                                                          "Use All Processors");
                                       }
                                       { jLabel3 = new JLabel(
                                                     );
                                         threadsPanel.add(
                                                        jLabel3);
                                         jLabel3.setText(
                                                   "Threads:");
                                       }
                                       { threadTextField =
                                           new JTextField(
                                             );
                                         threadsPanel.add(
                                                        threadTextField);
                                         threadTextField.
                                           setText(
                                             "1");
                                         threadTextField.
                                           setPreferredSize(
                                             new Dimension(
                                               50,
                                               20)); } } }
                                   { rendererPanel = new JPanel(
                                                       );
                                     FlowLayout rendererPanelLayout =
                                       new FlowLayout(
                                       );
                                     rendererPanelLayout.
                                       setAlignment(
                                         FlowLayout.
                                           LEFT);
                                     rendererPanel.setLayout(
                                                     rendererPanelLayout);
                                     this.addTab("Renderer",
                                                 null,
                                                 rendererPanel,
                                                 null);
                                     { defaultRendererRadioButton =
                                         new JRadioButton(
                                           );
                                       rendererPanel.add(
                                                       defaultRendererRadioButton);
                                       defaultRendererRadioButton.
                                         setText(
                                           "Default Renderer");
                                     }
                                     { bucketRendererPanel =
                                         new JPanel(
                                           );
                                       BoxLayout bucketRendererPanelLayout =
                                         new BoxLayout(
                                         bucketRendererPanel,
                                         BoxLayout.
                                           Y_AXIS);
                                       bucketRendererPanel.
                                         setLayout(
                                           bucketRendererPanelLayout);
                                       rendererPanel.add(
                                                       bucketRendererPanel);
                                       bucketRendererPanel.
                                         setBorder(
                                           BorderFactory.
                                             createTitledBorder(
                                               BorderFactory.
                                                 createEtchedBorder(
                                                   BevelBorder.
                                                     LOWERED),
                                               "Bucket Renderer",
                                               TitledBorder.
                                                 LEADING,
                                               TitledBorder.
                                                 TOP));
                                       { bucketRendererRadioButton =
                                           new JRadioButton(
                                             );
                                         bucketRendererPanel.
                                           add(
                                             bucketRendererRadioButton);
                                         bucketRendererRadioButton.
                                           setText(
                                             "Enable"); }
                                       { samplingPanel = new JPanel(
                                                           );
                                         GridLayout samplingPanelLayout =
                                           new GridLayout(
                                           2,
                                           2);
                                         samplingPanelLayout.
                                           setColumns(
                                             2);
                                         samplingPanelLayout.
                                           setHgap(
                                             5);
                                         samplingPanelLayout.
                                           setVgap(
                                             5);
                                         samplingPanelLayout.
                                           setRows(
                                             2);
                                         samplingPanel.setLayout(
                                                         samplingPanelLayout);
                                         bucketRendererPanel.
                                           add(
                                             samplingPanel);
                                         { jLabel5 = new JLabel(
                                                       );
                                           samplingPanel.
                                             add(
                                               jLabel5);
                                           jLabel5.setText(
                                                     "Min:");
                                         }
                                         { javax.swing.ComboBoxModel minSamplingComboBoxModel =
                                             new javax.swing.DefaultComboBoxModel(
                                             new String[] { "Item One",
                                             "Item Two" });
                                           minSamplingComboBox =
                                             new javax.swing.JComboBox(
                                               );
                                           samplingPanel.
                                             add(
                                               minSamplingComboBox);
                                           minSamplingComboBox.
                                             setModel(
                                               minSamplingComboBoxModel);
                                         }
                                         {
                                             jLabel6 =
                                               new JLabel(
                                                 );
                                             samplingPanel.
                                               add(
                                                 jLabel6);
                                             jLabel6.
                                               setText(
                                                 "Max:");
                                         }
                                         {
                                             javax.swing.ComboBoxModel maxSamplingComboxBoxModel =
                                               new javax.swing.DefaultComboBoxModel(
                                               new String[] { "Item One",
                                               "Item Two" });
                                             maxSamplingComboxBox =
                                               new javax.swing.JComboBox(
                                                 );
                                             samplingPanel.
                                               add(
                                                 maxSamplingComboxBox);
                                             maxSamplingComboxBox.
                                               setModel(
                                                 maxSamplingComboxBoxModel);
                                         }
                                       } }
                                   } }
                             catch (Exception e) {
                                 e.
                                   printStackTrace(
                                     );
                             } }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "8V+K5qZ6lxsAAA==");
}
