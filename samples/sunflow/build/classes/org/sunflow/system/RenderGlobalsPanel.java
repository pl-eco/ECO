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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
