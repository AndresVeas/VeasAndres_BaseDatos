package UserInterface.Form;

import javax.swing.*;

import BusinessLogic.LibroBL;
import DataAccess.DTO.LibroDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import UserInterface.IAStyle;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibroPanel extends JPanel implements ActionListener {
    private Integer idLibro = 0, idMaxLibro=0;
    private LibroDTO libro = null;
    private LibroBL libroBL = null;
    private Integer cantidadPaginas = 0, paginaActual=0, filasPagina = 10;
    private String [] Estados;
    private JComboBox<String> cmbEstado = new JComboBox<>();

    public LibroPanel () {
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
        idLibro      = 1;
        libroBL      = new LibroBL();
        libro        = libroBL.getByIdLibro(idLibro);
        idMaxLibro   = libroBL.getMaxRow();
        cmbEstado.setSelectedIndex(loadEstado());
        cantidadPaginas = (int) Math.ceil((double) LibroBL.getAll().size() / filasPagina);
    }

    private void showRow() {
        boolean libroNull = (libro == null);
        txtIdLibro.setText((libroNull) ?        "" : libro.getIdLibro().toString());
        txtNombreLibro.setText((libroNull) ?    "" : libro.getNombreLibro());
        txtNombreAutor.setText((libroNull) ?    "" : libro.getNombreAutor());
        txtApellidoAutor.setText((libroNull) ?  "" : libro.getApellidoAutor());
        txtEditorial.setText((libroNull) ?      "" : libro.getEditorial());
        cmbEstado.setVisible(true);
        lblTotalReg.setText(idLibro.toString() + " de " + idMaxLibro.toString());
    }

    private void btnNuevoClick() {
        libro = null;
        cmbEstado.setSelectedIndex(0);
        showRow();
    } 
    
    private void btnGuardarClick() {
        boolean libroNull = (libro == null);
        try {
            if (IAStyle.showConfirmYesNo("¿Seguro que desea " + ((libroNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))){
            
                if (libroNull){
                    libro = new LibroDTO();
                    libro.setNombreLibro(txtNombreLibro.getText());
                    libro.setNombreAutor(txtNombreAutor.getText());
                    libro.setApellidoAutor(txtApellidoAutor.getText());
                    libro.setEditorial(txtEditorial.getText());
                    libro.setEstado(cmbEstado.getSelectedItem().toString());
                }                    
                else
                    libro.setNombreLibro(txtNombreLibro.getText());
                    libro.setNombreAutor(txtNombreAutor.getText());
                    libro.setApellidoAutor(txtApellidoAutor.getText());
                    libro.setEditorial(txtEditorial.getText());
                    libro.setEstado(cmbEstado.getSelectedItem().toString());
    
                if(!((libroNull) ? libroBL.create(libro) : libroBL.update(libro)))
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

                if (!libroBL.delete(libro.getIdLibro()))
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
            if(libro == null)
                loadRow();
            showRow();
        } catch (Exception e) {}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            idLibro = 1;
        if (e.getSource() == btnRowAnt && (idLibro > 1))
            idLibro--;
        if (e.getSource() == btnRowSig && (idLibro < idMaxLibro))
            idLibro++;
        if (e.getSource() == btnRowFin)
            idLibro = idMaxLibro;
    
        if (e.getSource() == btnPageIni)
            paginaActual = 0;
        if (e.getSource() == btnPageAnt && paginaActual>0)
            paginaActual --;
        if (e.getSource() == btnPageSig && paginaActual<cantidadPaginas-1)
            paginaActual++;
        if (e.getSource() == btnPageFin && cantidadPaginas > 0)
            paginaActual = cantidadPaginas - 1;
        try {
            libro = libroBL.getByIdLibro(idLibro);
            cmbEstado.setSelectedIndex(loadEstado());
            showRow();
            showTable();
        } catch (Exception ex) {
            IAStyle.showMsgError(ex.getMessage());
        }
    }

    private void showTable() throws Exception {
        lblTotalPag.setText((paginaActual + 1) + " de " + cantidadPaginas);
        
        List<LibroDTO> allLibro = LibroBL.getAll();
        String[] header = {"Id", "Nombre Libro","Nombre Autor","Apellido Autor","Editorial", "Estado"};
        Object[][] data = new Object[filasPagina][header.length];
        int index = 0, inicio = paginaActual * filasPagina, end = Math.min(inicio + filasPagina, allLibro.size());
        
        for (int i = inicio; i < end; i++) {
            LibroDTO l = allLibro.get(i);
            data[index][0] = l.getIdLibro();
            data[index][1] = l.getNombreLibro();
            data[index][2] = l.getNombreAutor();
            data[index][3] = l.getApellidoAutor();
            data[index][4] = l.getEditorial();
            data[index][5] = l.getEstado();

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
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre Libro
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Autor
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Editorial
        table.getColumnModel().getColumn(4).setPreferredWidth(200); // Editorial
        table.getColumnModel().getColumn(5).setPreferredWidth(50); // Editorial


        pnlTabla.removeAll();
        pnlTabla.add(new JScrollPane(table));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String strIdLibro = table.getModel().getValueAt(row, 0).toString();
                    idLibro = Integer.parseInt(strIdLibro);
                    try {
                        libro = libroBL.getByIdLibro(idLibro);
                        showRow();
                    } catch (Exception ignored) {
                    }
                    System.out.println("Tabla.Selected: " + strIdLibro);
                }
            }
        });

        
    }

    private void CargarEstados () throws Exception {
        List<LibroDTO> libros = LibroBL.getAll(); 
        Set<String> estadosUnicos = new HashSet<>(); 

        for (LibroDTO libro : libros) {
            estadosUnicos.add(libro.getEstado());
        }
        Estados = estadosUnicos.toArray(new String[0]); 
        cmbEstado.removeAllItems();
        for (String estado : Estados) {
            cmbEstado.addItem(estado);
        }
    }

    private int loadEstado (){
        for (int i = 0; i < Estados.length; i++) {
            if (libro.getEstado().equals(Estados[i])){
                return i;
            }
        }
        return 0;
    }

/************************
 * FormDesing : Grupo6
 ************************/ 
    private PatLabel 
            lblTitulo           = new PatLabel("LIBROS"),
            lblIdLibro          = new PatLabel("Codigo         : "),
            lblNombreLibro      = new PatLabel("Nombre Libro   : "),
            lblNombreAutor      = new PatLabel("Nombre Autor   : "),
            lblApellidoAutor    = new PatLabel("Apellido Autor : "),
            lblEditorial        = new PatLabel("Editorial      : "),
            lblEstado        = new PatLabel("Estado      : "),
            lblTotalReg         = new PatLabel(" 0 de 0 "),
            lblTotalPag         = new PatLabel(" 0 ");
    private PatTextBox 
            txtIdLibro          = new PatTextBox(),
            txtNombreLibro      = new PatTextBox(),
            txtNombreAutor      = new PatTextBox(),
            txtApellidoAutor    = new PatTextBox(),
            txtEditorial        = new PatTextBox();
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
        
        txtIdLibro.setEditable(false);   
        txtIdLibro.setDisabledTextColor(txtIdLibro.getForeground());
        txtIdLibro.setEnabled(false);
        txtIdLibro.setBorderLine();

        txtNombreLibro.setBorderLine();
        txtNombreAutor.setBorderLine();  
        txtApellidoAutor.setBorderLine();
        txtEditorial.setBorderLine();    
        
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

        gbc.ipady = 0;
        gbc.ipadx = 0;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 0;
        gbc.insets = new Insets(0, 0, 0, 0);  // Ajusta el valor superior a 50
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
        add(lblIdLibro, gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1; 
        add(txtIdLibro, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        add(lblNombreLibro, gbc);
        gbc.gridy = 6;
        gbc.gridx = 1;
        add(txtNombreLibro, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        add(lblNombreAutor, gbc);
        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1; 
        add(txtNombreAutor, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        add(lblApellidoAutor, gbc);
        gbc.gridy = 8;
        gbc.gridx = 1;
        add(txtApellidoAutor, gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        add(lblEditorial, gbc);
        gbc.gridy = 9;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1; 
        add(txtEditorial, gbc);
        
        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblEstado, gbc);
        gbc.gridy = 10;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(cmbEstado, gbc); 

        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(pnlBtnCRUD, gbc);
    }
}