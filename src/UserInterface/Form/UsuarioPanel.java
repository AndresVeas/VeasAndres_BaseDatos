package UserInterface.Form;

import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;
import UserInterface.IAStyle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;

public class UsuarioPanel extends JPanel implements ActionListener {
    private Integer idUsuario = 0, idMaxUsuario=0;
    private UsuarioDTO usuario = null;
    private UsuarioBL usuarioBL = null;
    private Integer cantidadPaginas = 0, paginaActual=0, filasPagina = 10;
    private String [] Estados;
    private JComboBox<String> cmbEstado = new JComboBox<>();
    
    public UsuarioPanel () {
        try {
            customizeComponent();
            CargarEstados();
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
        idUsuario      = 1;
        usuarioBL      = new UsuarioBL();
        usuario        = usuarioBL.getByIdUsuario(idUsuario);
        idMaxUsuario   = usuarioBL.getMaxRow();
        cmbEstado.setSelectedIndex(loadEstado());
        cantidadPaginas = (int) Math.ceil((double) UsuarioBL.getAll().size() / filasPagina);
    }

    private void showRow() {
        boolean usuarioNull = (usuario == null);
        txtIdUsuario.setText((usuarioNull) ? "" : usuario.getIdUsuario().toString());
        txtNombre.setText((usuarioNull) ?    "" : usuario.getNombre());
        txtApellido.setText((usuarioNull) ?  "" : usuario.getApellido());
        cmbEstado.setVisible(true);

        lblTotalReg.setText(idUsuario.toString() + " de " + idMaxUsuario.toString());
    }

    private void btnNuevoClick() {
        usuario = null;
        cmbEstado.setSelectedIndex(0);
        showRow();
    } 
    
    private void btnGuardarClick() {
        boolean usuarioNull = (usuario == null);
        try {
            if (IAStyle.showConfirmYesNo("¿Seguro que desea " + ((usuarioNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))){
            
                if (usuarioNull) usuario = new UsuarioDTO();
                    
                usuario.setNombre(txtNombre.getText());
                usuario.setApellido(txtApellido.getText());
                usuario.setEstado(cmbEstado.getSelectedItem().toString());
                
                if(!((usuarioNull) ? usuarioBL.create(usuario) : usuarioBL.update(usuario)))
                    IAStyle.showMsgError("Error al guardar...!");
    
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

                if (!usuarioBL.delete(usuario.getIdUsuario()))
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
            if(usuario == null)
                loadRow();
                showRow();
        } catch (Exception e) {}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            idUsuario = 1;
        if (e.getSource() == btnRowAnt && (idUsuario > 1))
            idUsuario--;
        if (e.getSource() == btnRowSig && (idUsuario < idMaxUsuario))
            idUsuario++;
        if (e.getSource() == btnRowFin)
            idUsuario = idMaxUsuario;
    
        if (e.getSource() == btnPageIni)
            paginaActual = 0;
        if (e.getSource() == btnPageAnt && paginaActual>0)
            paginaActual --;
        if (e.getSource() == btnPageSig && paginaActual<cantidadPaginas-1)
            paginaActual++;
        if (e.getSource() == btnPageFin && cantidadPaginas > 0)
            paginaActual = cantidadPaginas - 1;
        try {
            usuario = usuarioBL.getByIdUsuario(idUsuario);
            cmbEstado.setSelectedIndex(loadEstado());
            showRow();
            showTable();
        } catch (Exception ex) {
            IAStyle.showMsgError(ex.getMessage());
        }
    }

    private void showTable() throws Exception {
        lblTotalPag.setText((paginaActual + 1) + " de " + cantidadPaginas);
    
        List<UsuarioDTO> allUsuario = UsuarioBL.getAll();
        String[] header = {"Id", "Nombre Usuario","Apellido Usuario","Estado"};
        Object[][] data = new Object[filasPagina][header.length];
        int index = 0, inicio = paginaActual * filasPagina, end = Math.min(inicio + filasPagina, allUsuario.size());
        for (int i = inicio; i < end; i++) {
            UsuarioDTO u = allUsuario.get(i);
            data[index][0] = u.getIdUsuario();
            data[index][1] = u.getNombre();
            data[index][2] = u.getApellido();
            data[index][3] = u.getEstado();
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
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre Usuario
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Autor
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Editorial

        pnlTabla.removeAll();
        pnlTabla.add(new JScrollPane(table));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String strIdUsuario = table.getModel().getValueAt(row, 0).toString();
                    idUsuario = Integer.parseInt(strIdUsuario);
                    try {
                        usuario = usuarioBL.getByIdUsuario(idUsuario);
                        showRow();
                    } catch (Exception ignored) {
                    }
                    System.out.println("Tabla.Selected: " + strIdUsuario);
                }
            }
        });
    }

    private void CargarEstados () throws Exception {
        List<UsuarioDTO> usuarios = UsuarioBL.getAll(); 
        Set<String> estadosUnicos = new HashSet<>(); 
        for (UsuarioDTO usuario : usuarios) {
            estadosUnicos.add(usuario.getEstado()); 
        }
        Estados = estadosUnicos.toArray(new String[0]); 
        cmbEstado.removeAllItems();
        for (String estado : Estados) {
            cmbEstado.addItem(estado);
        }
    }

    private int loadEstado (){
        for (int i = 0; i < Estados.length; i++) {
            if (usuario.getEstado().equals(Estados[i])){
                return i;
            }
        }
        return 0;
    }
/************************
 * FormDesing : Grupo6
 ************************/ 
    private PatLabel 
            lblTitulo      = new PatLabel("USUARIOS"),
            lblIdUsuario   = new PatLabel("Codigo : "),
            lblNombre      = new PatLabel("Nombre Usuario : "),
            lblApellido    = new PatLabel("Apellido Usuario : "),
            lblEstado      = new PatLabel("Estado : "),
            lblTotalReg    = new PatLabel(" 0 de 0 "),
            lblTotalPag    = new PatLabel(" 0 ");
    private PatTextBox 
            txtIdUsuario   = new PatTextBox(),
            txtNombre      = new PatTextBox(),
            txtApellido    = new PatTextBox();

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

    public void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        txtIdUsuario.setEditable(false);   
        txtIdUsuario.setDisabledTextColor(txtIdUsuario.getForeground());
        txtIdUsuario.setEnabled(false);
        txtIdUsuario.setBorderLine();
        txtNombre.setBorderLine();   
        txtApellido.setBorderLine();   

        cmbEstado.setPreferredSize(new Dimension(200, 25));
        
        pnlBtnPage.add(btnPageIni);
        pnlBtnPage.add(btnPageAnt);
        pnlBtnPage.add(new PatLabel(" Page:( "));
        pnlBtnPage.add(lblTotalPag); //cambiar
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

        gbc.ipady = 1;
        gbc.ipadx = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 0, 0);  // Ajusta el valor superior a 50
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createRigidArea(new Dimension(0,0)), gbc);

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
        add(lblIdUsuario, gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1; 
        add(txtIdUsuario, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        add(lblNombre, gbc);
        gbc.gridy = 6;
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        add(lblApellido, gbc);
        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1; 
        add(txtApellido, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblEstado, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(cmbEstado, gbc); 

        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(pnlBtnCRUD, gbc);
    }
}