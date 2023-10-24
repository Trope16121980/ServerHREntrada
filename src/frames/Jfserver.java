package frames;

/**
 *
 * @author Gustavo_Senorans
 */
public class Jfserver extends javax.swing.JFrame {

    /**
     * Creates new form jfServer
     */
    
    public Jfserver() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
//        setIconImage(new ImageIcon(getClass().getResource("/img/recorteIco.jpg")).getImage());
        txtEstado.setText("Servidor conectado");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelServer = new javax.swing.JPanel();
        jlabelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();
        jlStatus = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio Server HREntradas");
        setName("frameServer"); // NOI18N
        setSize(new java.awt.Dimension(2, 2));

        jPanelServer.setBackground(java.awt.Color.white);

        jlabelTitulo.setBackground(java.awt.Color.white);
        jlabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlabelTitulo.setForeground(new java.awt.Color(0, 102, 153));
        jlabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabelTitulo.setText("Server HREntrada");
        jlabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextArea.setEditable(false);
        jTextArea.setBackground(java.awt.Color.white);
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        btnSalir.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSalir.setText("Apagar");
        btnSalir.setToolTipText("Cerrar el servidor");
        btnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jlStatus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlStatus.setText("Estado:");

        txtEstado.setEditable(false);
        txtEstado.setBackground(new java.awt.Color(255, 255, 255));
        txtEstado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEstado.setForeground(new java.awt.Color(153, 255, 0));
        txtEstado.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEstado.setToolTipText("Estado del servidor");
        txtEstado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanelServerLayout = new javax.swing.GroupLayout(jPanelServer);
        jPanelServer.setLayout(jPanelServerLayout);
        jPanelServerLayout.setHorizontalGroup(
            jPanelServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelServerLayout.createSequentialGroup()
                        .addComponent(jlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelServerLayout.setVerticalGroup(
            jPanelServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelServer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanelServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JLabel jlabelTitulo;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables

    public void appendText(String valueOf) {
        jTextArea.append(valueOf.formatted("UTF-8"));
    }

}
