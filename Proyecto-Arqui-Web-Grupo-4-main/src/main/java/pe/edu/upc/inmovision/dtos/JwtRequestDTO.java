package pe.edu.upc.inmovision.dtos;

public class JwtRequestDTO {
    private String correo;
    private String password;
    public JwtRequestDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    public JwtRequestDTO(String correo, String password) {
        super();
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }
    public String getPassword() {
        return password;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
