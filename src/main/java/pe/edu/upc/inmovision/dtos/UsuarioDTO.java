    package pe.edu.upc.inmovision.dtos;


    import java.time.LocalDate;


    public class UsuarioDTO {
        private int usuarioId;
        private int rolId;
        private String rolName;
        private String nombre;
        private String apellido;
        private String correo;
        private String contrasena;
        private String telefono;
        private String fotoUrl;
        private LocalDate fechaRegistro;
        private Boolean enabled;


        public int getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(int usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getFotoUrl() {
            return fotoUrl;
        }

        public void setFotoUrl(String fotoUrl) {
            this.fotoUrl = fotoUrl;
        }

        public LocalDate getFechaRegistro() {
            return fechaRegistro;
        }

        public void setFechaRegistro(LocalDate fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }

        public int getRolId() {
            return rolId;
        }

        public String getRolName() {
            return rolName;
        }

        public void setRolName(String rolName) {
            this.rolName = rolName;
        }

        public void setRolId(int rolId) {
            this.rolId = rolId;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }
