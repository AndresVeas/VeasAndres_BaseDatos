package UserInterface.Form;

import BusinessLogic.PrestamoBL;
import DataAccess.DTO.PrestamoDTO;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;
import UserInterface.IAStyle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

public class PrestamoPanel extends JPanel implements ActionListener {
    private Integer idPrestamo = 0, idMaxPrestamo=0;
    private PrestamoDTO prestamo = null;
    private PrestamoBL prestamoBL = null;
    private Integer cantidadPaginas = 0, paginaActual=0, filasPagina = 10;
    private JComboBox<String> cmbEstadoPrestamo = new JComboBox<>(new String[]{"Prestado", "Perdido", "Devuelto"});

    public PrestamoPanel () {
        try {
            customizeComponent();
            loadRow();
            showRow();
            showTable();

            btnRowIni.addActionListener(this);
            btnRowAnt.addActionListener(this);
            btnRowSig.addActionListener(this);
            btnRowFin.addActionListener(this);

            btnPageIni.addActionListener(this);
            btnPageAnt.addActionListener(this);
            btnPageSig.addActionListener(this);
            btnPageFin.addActionListener(this);

            btnNuevo.addActionListener   (  e -> btnNuevoClick());
            btnGuardar.addActionListener (  e -> btnGuardarClick());
            btnEliminar.addActionListener(  e -> btnEliminarClick());
            btnCancelar.addActionListener(  e -> btnCancelarClick());
        } catch (Exception e) {
            IAStyle.showMsg(e.getMessage());
        }
    }

    private void loadRow() throws Exception {
        idPrestamo      = 1;
        prestamoBL      = new PrestamoBL();
        prestamo        = prestamoBL.getByIdPrestamo(idPrestamo);
        idMaxPrestamo   = prestamoBL.getMaxRow();
        cantidadPaginas = (int) Math.ceil((double) prestamoBL.getAll().size() / filasPagina);
    }

    private void showRow() {
        boolean prestamoNull = (prestamo == null);
        txtIdPrestamo.setText((prestamoNull) ? "" : prestamo.getIdPrestamo().toString());
        txtNombreUsuario.setText((prestamoNull) ? "" : prestamo.getNombreUsuario());
        txtApellidoUsuario.setText((prestamoNull) ? "" : prestamo.getApellidoUsuario());
        txtNombreLibro.setText((prestamoNull) ? "" : prestamo.getNombreLibro());
        txtEstadoPrestamo.setText((prestamoNull)? "": prestamo.getEstadoPrestamo());
    
        if (prestamoNull) {
            // Si es un nuevo préstamo, mostrar el JComboBox y ocultar el campo de texto
            cmbEstadoPrestamo.setVisible(true);
            txtEstadoPrestamo.setVisible(false); // Si aún tienes este campo, ocúltalo
            cmbEstadoPrestamo.setSelectedIndex(0); // Selecciona la primera opción por defecto
        } else {
            // Si es un préstamo existente, mostrar el campo de texto y ocultar el JComboBox
            txtEstadoPrestamo.setVisible(true); // Si aún tienes este campo, muéstralo
            cmbEstadoPrestamo.setVisible(false);
        }
    
        lblTotalReg.setText(idPrestamo.toString() + " de " + idMaxPrestamo.toString());
    }

    private void btnNuevoClick() {
        prestamo = null; // Reiniciar el objeto préstamo
        showRow(); // Mostrar los campos vacíos
    
        // Mostrar el JComboBox y ocultar el campo de texto (si lo hay)
        cmbEstadoPrestamo.setVisible(true);
        txtEstadoPrestamo.setVisible(false); // Si aún tienes este campo, ocúltalo
    
        // Establecer el valor predeterminado del JComboBox
        cmbEstadoPrestamo.setSelectedIndex(0); // Selecciona la primera opción por defecto
    }
    
    private void btnGuardarClick() {
        boolean prestamoNull = (prestamo == null);
        try {
            if (IAStyle.showConfirmYesNo("¿Seguro que desea " + ((prestamoNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))) {
                if (prestamoNull) {
                    prestamo = new PrestamoDTO();
                }
                prestamo.setNombreUsuario(txtNombreUsuario.getText());
                prestamo.setApellidoUsuario(txtApellidoUsuario.getText());
                prestamo.setNombreLibro(txtNombreLibro.getText());
    
                // Obtener el estado del préstamo desde el JComboBox si está visible
                if (cmbEstadoPrestamo.isVisible()) {
                    prestamo.setEstadoPrestamo((String) cmbEstadoPrestamo.getSelectedItem());
                } else {
                    prestamo.setEstadoPrestamo(txtEstadoPrestamo.getText()); // Si aún tienes este campo
                }
    
                if (!((prestamoNull) ? prestamoBL.create(prestamo) : prestamoBL.update(prestamo))) {
                    IAStyle.showMsgError("Error al guardar...!");
                }
    
                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            IAStyle.showMsgError(e.getMessage());
        }
    }

    private void btnEliminarClick() {
        try {
            if (IAStyle.showConfirmYesNo("Seguro que desea Eliminar?")) {

                if (!prestamoBL.delete(prestamo.getIdPrestamo()))
                    throw new Exception("Error al eliminar...!");
    
                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            IAStyle.showMsgError(e.getMessage());
        }
    }

    private void btnCancelarClick() {
        try {
            if (prestamo == null) {
                loadRow(); // Cargar la fila actual
            }
            showRow(); // Mostrar los datos actuales
    
            // Ocultar el JComboBox y mostrar el campo de texto (si lo hay)
            cmbEstadoPrestamo.setVisible(false);
            txtEstadoPrestamo.setVisible(true); // Si aún tienes este campo, muéstralo
        } catch (Exception e) {
            IAStyle.showMsgError(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            idPrestamo = 1;
        if (e.getSource() == btnRowAnt && (idPrestamo > 1))
            idPrestamo--;
        if (e.getSource() == btnRowSig && (idPrestamo < idMaxPrestamo))
            idPrestamo++;
        if (e.getSource() == btnRowFin)
            idPrestamo = idMaxPrestamo;
    
        if (e.getSource() == btnPageIni)
            paginaActual = 0;
        if (e.getSource() == btnPageAnt && paginaActual>0)
            paginaActual --;
        if (e.getSource() == btnPageSig && paginaActual<cantidadPaginas-1)
            paginaActual++;
        if (e.getSource() == btnPageFin && cantidadPaginas > 0)
            paginaActual = cantidadPaginas - 1;
        try {
            prestamo = prestamoBL.getByIdPrestamo(idPrestamo);  
            showRow();
            showTable();
        } catch (Exception ex) {
            IAStyle.showMsgError(ex.getMessage());
        }
    }

    private void showTable() throws Exception {
        lblTotalPag.setText((paginaActual + 1) + " de " + cantidadPaginas);

        List<PrestamoDTO> allPrestamo = prestamoBL.getAll();
        String[] header = {"Id", "Nombre Usuario","Apellido Usuario","Libro","EstadoPrestamo"};
        Object[][] data = new Object[filasPagina][header.length];
        int index = 0, inicio = paginaActual * filasPagina, end = Math.min(inicio + filasPagina, allPrestamo.size());
        for (int i = inicio; i < end; i++) {
            PrestamoDTO p = allPrestamo.get(i);
            data[index][0] = p.getIdPrestamo();
            data[index][1] = p.getNombreUsuario();
            data[index][2] = p.getApellidoUsuario();
            data[index][3] = p.getNombreLibro();
            data[index][4] = p.getEstadoPrestamo();
            index++;
        }

        JTable table = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // Código
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre Prestamo
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Autor
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Editorial
        table.getColumnModel().getColumn(4).setPreferredWidth(200); // Editorial


        pnlTabla.removeAll();
        pnlTabla.add(new JScrollPane(table));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String strIdPrestamo = table.getModel().getValueAt(row, 0).toString();
                    idPrestamo = Integer.parseInt(strIdPrestamo);
                    try {
                        prestamo = prestamoBL.getByIdPrestamo(idPrestamo);
                        showRow();
                    } catch (Exception ignored) {
                    }
                    System.out.println("Tabla.Selected: " + strIdPrestamo);
                }
            }
        });

        
    }

/************************
 * FormDesing : Grupo6
 ************************/ 
    private PatLabel 
            lblTitulo           = new PatLabel("PRESTAMO"),
            lblIdPrestamo       = new PatLabel("Codigo           : "),
            lblNombreUsuario    = new PatLabel("Nombre Usuario   : "),
            lblApellidoUsuario  = new PatLabel("Apellido Usuario : "),
            lblNombreLibro      = new PatLabel("Nombre Libro     : "),
            lblEstadoPrestamo   = new PatLabel("Estado Prestamo  : "),
            lblTotalReg         = new PatLabel(" 0 de 0 "),
            lblTotalPag         = new PatLabel(" 0 ");
    private PatTextBox 
            txtIdPrestamo        = new PatTextBox(),
            txtNombreUsuario     = new PatTextBox(),
            txtApellidoUsuario   = new PatTextBox(),
            txtNombreLibro       = new PatTextBox(),
            txtEstadoPrestamo    = new PatTextBox();

    private PatButton 
            btnPageIni  = new PatButton(" |< "),
            btnPageAnt  = new PatButton(" << "),
            btnPageSig  = new PatButton(" >> "),
            btnPageFin  = new PatButton(" >| "),

            btnRowIni   = new PatButton(" |< "),
            btnRowAnt   = new PatButton(" << "),
            btnRowSig   = new PatButton(" >> "),
            btnRowFin   = new PatButton(" >| "),

            btnNuevo    = new PatButton("Nuevo"),
            btnGuardar  = new PatButton("Guardar"),
            btnCancelar = new PatButton("Cancelar"),
            btnEliminar = new PatButton("Eliminar");
    private JPanel 
            pnlTabla    = new JPanel(),
            pnlBtnRow   = new JPanel(new FlowLayout()),
            pnlBtnPage  = new JPanel(new FlowLayout()),
            pnlBtnCRUD  = new JPanel(new FlowLayout());

    private void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
            
        txtIdPrestamo.setEnabled(false);
        txtIdPrestamo.setBorderLine();
        txtNombreUsuario.setBorderLine();
        txtApellidoUsuario.setBorderLine();
        txtNombreLibro.setBorderLine();
        txtEstadoPrestamo.setBorderLine();
            
        cmbEstadoPrestamo.setPreferredSize(new Dimension(200, 25)); // Ajustar tamaño si es necesario
            
        pnlBtnPage.add(btnPageIni);
        pnlBtnPage.add(btnPageAnt);
        pnlBtnPage.add(new PatLabel(" Page:( "));
        pnlBtnPage.add(lblTotalPag);
        pnlBtnPage.add(new PatLabel(" ) "));
        pnlBtnPage.add(btnPageSig);
        pnlBtnPage.add(btnPageFin);
            
        pnlBtnRow.add(btnRowIni);
        pnlBtnRow.add(btnRowAnt);
        pnlBtnRow.add(lblTotalReg);
        pnlBtnRow.add(btnRowSig);
        pnlBtnRow.add(btnRowFin);
            
        pnlBtnCRUD.add(btnNuevo);
        pnlBtnCRUD.add(btnGuardar);
        pnlBtnCRUD.add(btnCancelar);
        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.setBorder(IAStyle.createBorderRect());
            
        gbc.insets = new Insets(0, 0, 0, 0);
            
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);
            
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("■ Sección de datos: "), gbc);
            
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(pnlBtnPage, gbc);
            
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 0;
        gbc.ipady = 200;
        gbc.ipadx = 900;
        pnlTabla.add(new Label("Loading data..."));
        add(pnlTabla, gbc);
            
        gbc.ipady = 0;
        gbc.ipadx = 0;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(Box.createRigidArea(new Dimension(0, 0)), gbc);
            
        gbc.insets = new Insets(30, 0, 0, 0);
            
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("■ Sección de registros: "), gbc);
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(pnlBtnRow, gbc);
            
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblIdPrestamo, gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(txtIdPrestamo, gbc);
            
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblNombreUsuario, gbc);
        gbc.gridy = 6;
        gbc.gridx = 1;
        add(txtNombreUsuario, gbc);
            
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblApellidoUsuario, gbc);
        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(txtApellidoUsuario, gbc);
            
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblNombreLibro, gbc);
        gbc.gridy = 8;
        gbc.gridx = 1;
        add(txtNombreLibro, gbc);
            
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblEstadoPrestamo, gbc);
        gbc.gridy = 9;
        gbc.gridx = 1;
        add(txtEstadoPrestamo, gbc);
        gbc.gridy = 9;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(cmbEstadoPrestamo, gbc); 
            
        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(pnlBtnCRUD, gbc);
    }
}
