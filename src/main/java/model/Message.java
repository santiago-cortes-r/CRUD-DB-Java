
package model;

public class Message {
    int id_Message;
    String Message;
    String autor_Message;
    String fecha_Message;

    public Message(){
    }

    public Message(String Message, String autor_Message, String fecha_Message) {
        this.Message = Message;
        this.autor_Message = autor_Message;
        this.fecha_Message = fecha_Message;
    }



    public int getId_Message() {
        return id_Message;
    }

    public void setId_Message(int id_Message) {
        this.id_Message = id_Message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getAutor_Message() {
        return autor_Message;
    }

    public void setAutor_Message(String autor_Message) {
        this.autor_Message = autor_Message;
    }

    public String getFecha_Message() {
        return fecha_Message;
    }

    public void setFecha_Message(String fecha_Message) {
        this.fecha_Message = fecha_Message;
    }



}