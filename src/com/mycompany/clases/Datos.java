package com.mycompany.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datos {

    private Connection cnn;

    public Datos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/facturacion";
            cnn = DriverManager.getConnection(db, "root", "");
        } catch (Exception ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validarUsuario(String usuario, String clave) {
        try {
            String sql = "select (1) from usuarios where idUsuario = '"
                    + usuario + "' and clave = '" + clave + "'";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int getPerfil(String usuario) {
        try {
            String sql = "select idPerfil from usuarios where idUsuario = '"
                    + usuario + "'";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("idPerfil");
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public boolean existeUsuario(String usuario) {
        try {
            String sql = "select (1) from usuarios where idUsuario = '"
                    + usuario + "'";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean existeCliente(String cliente) {
        try {
            String sql = "select (1) from clientes where idCliente = '"
                    + cliente + "'";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean existeProducto(String producto) {
        try {
            String sql = "select (1) from productos where idProducto = '"
                    + producto + "'";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String agregarUsuario(Usuario miUsuario) {
        try {
            String sql = "insert into usuarios values('"
                    + miUsuario.getIdUsuario() + "', '"
                    + miUsuario.getNombres() + "', '"
                    + miUsuario.getApellidos() + "', '"
                    + miUsuario.getClave() + "', "
                    + miUsuario.getPerfil() + ")";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Usuario agregado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar insertar el usuario";
        }
    }

    public String agregarProducto(Producto miProducto) {
        try {
            String sql = "insert into productos values('"
                    + miProducto.getIdProducto() + "', '"
                    + miProducto.getDescripcion() + "', "
                    + miProducto.getPrecio() + ", "
                    + miProducto.getIva() + ", '"
                    + miProducto.getNota() + "')";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Producto agregado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar insertar el producto";
        }
    }

    public String agregarCliente(Cliente miCliente) {
        try {
            String sql = "insert into clientes values('"
                    + miCliente.getIdCliente() + "', "
                    + miCliente.getIdTipo() + ", '"
                    + miCliente.getNombres() + "', '"
                    + miCliente.getApellidos() + "', '"
                    + miCliente.getDireccion() + "', '"
                    + miCliente.getTelefono() + "', "
                    + miCliente.getIdCiudad() + ", '"
                    + Utilidades.formatDate(miCliente.getFechaNacimiento()) + "', '"
                    + Utilidades.formatDate(miCliente.getFechaIngreso()) + "')";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Cliente agregado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar insertar el cliente";
        }
    }

    public String modificarUsuario(Usuario miUsuario) {
        try {
            String sql = "update usuarios set "
                    + "nombres = '" + miUsuario.getNombres() + "', "
                    + "apellidos = '" + miUsuario.getApellidos() + "', "
                    + "clave = '" + miUsuario.getClave() + "', "
                    + "idPerfil = " + miUsuario.getPerfil() + " "
                    + "where idUsuario = '" + miUsuario.getIdUsuario() + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Usuario modificado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar modificar el usuario";
        }
    }

    public String modificarCliente(Cliente miCliente) {
        try {
            String sql = "update clientes set "
                    + "idTipo = " + miCliente.getIdTipo() + ", "
                    + "nombres = '" + miCliente.getNombres() + "', "
                    + "apellidos = '" + miCliente.getApellidos() + "', "
                    + "direccion = '" + miCliente.getDireccion() + "', "
                    + "telefono = '" + miCliente.getTelefono() + "', "
                    + "idCiudad = " + miCliente.getIdCiudad() + ", "
                    + "fechaNacimiento = '" + Utilidades.formatDate(miCliente.getFechaNacimiento()) + "', "
                    + "fechaIngreso = '" + Utilidades.formatDate(miCliente.getFechaIngreso()) + "' "
                    + "where idCliente = '" + miCliente.getIdCliente() + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Cliente modificado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar modificar el cliente";
        }
    }

    public String modificarProducto(Producto miProducto) {
        try {
            String sql = "update productos set "
                    + "descripcion = '" + miProducto.getDescripcion() + "', "
                    + "precio = " + miProducto.getPrecio() + ", "
                    + "idIVA = " + miProducto.getIva() + ", "
                    + "notas = '" + miProducto.getNota() + "' "
                    + "where idProducto = '" + miProducto.getIdProducto() + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Producto modificado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar modificar el producto";
        }
    }

    public String borrarUsuario(String usuario) {
        try {
            String sql = "delete from usuarios where idUsuario = '" + usuario + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Usuario borrado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar borrar el usuario";
        }
    }

    public String borrarCliente(String cliente) {
        try {
            String sql = "delete from clientes where idCliente = '" + cliente + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Cliente borrado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar borrar el cliente";
        }
    }

    public String borrarProducto(String producto) {
        try {
            String sql = "delete from productos where idProducto = '" + producto + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            return "Producto borrado correctamente";
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un error al intentar borrar el producto";
        }
    }

    public ResultSet getUsuarios() {
        try {
            String sql = "select * from usuarios";
            Statement st = cnn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getClientes() {
        try {
            String sql = "select * from clientes";
            Statement st = cnn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getFacturas() {
        try {
            String sql = "select * from factura";
            Statement st = cnn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getConsulta(String sql) {
        try {
            Statement st = cnn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getProductos() {
        try {
            String sql = "select * from productos";
            Statement st = cnn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int numeroUsuarios() {
        try {
            String sql = "select count(*) as num from usuarios";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int numeroClientes() {
        try {
            String sql = "select count(*) as num from clientes";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int numeroProductos() {
        try {
            String sql = "select count(*) as num from productos";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public Producto getProducto(String idProducto) {
        try {
            Producto miProducto = null;
            String sql = "select * from productos "
                    + "where idProducto = '" + idProducto + "'";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                miProducto = new Producto(
                        rs.getString("idProducto"),
                        rs.getString("descripcion"),
                        rs.getInt("precio"),
                        rs.getInt("idIva"),
                        rs.getString("notas"));
            }
            return miProducto;
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getNumFac() {
        try {
            String sql = "select max(idFactura) as num from factura";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("num") + 1;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    public void agregarFactura(int idFactura, String idCliente, Date fecha) {
        try {
            String sql = "insert into factura values("
                    + idFactura + ", '"
                    + idCliente + "', '"
                    + Utilidades.formatDate(fecha) + "')";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarDetalleFactura(int idFactura, int idLinea, 
            String idProducto, String descripcion, int precio, int cantidad) {
        try {
            String sql = "insert into detalleFactura values("
                    + idFactura + ", "
                    + idLinea + ", '"
                    + idProducto + "', '"
                    + descripcion + "', "
                    + precio + ", "
                    + cantidad + ")";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioClave(String usuario, String clave) {
        try {
            String sql = "update usuarios set "
                    + "clave = '" + clave + "' "
                    + "where idUsuario = '" + usuario + "'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
