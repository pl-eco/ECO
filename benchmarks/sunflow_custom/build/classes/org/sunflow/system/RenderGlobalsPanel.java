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
      1163966490000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "AAA=");
}
