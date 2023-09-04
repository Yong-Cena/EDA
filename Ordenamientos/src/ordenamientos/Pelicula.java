
package ordenamientos;

public class Pelicula implements Comparable <Pelicula>{
    private int id;
    private int año;
    private String titulo;
    
    public Pelicula()
    {
        id=0;
        año=0;
        titulo= null;
    }
    
    public Pelicula(int id, int año, String titulo)
    {
        this.id= id;
        this.año= año;
        this.titulo= titulo;
    }

    public int getId() {
        return id;
    }

    public int getAño() {
        return año;
    }

    public String getTitulo() {
        return titulo;
    }

    //comparacion respecto al año
    public int compareTo(Pelicula otra)
    {
        int res;
        if(año==otra.año)
        {
            res=0;
        }
        else
        {
            if(año>otra.año)
            {
                res=1;
            }
            else
            {
                res=-1;
            }
        }
        return res;
    }
    
    public String toString()
    {
        String resp="";
        resp+= "ID: "+ id+ " Año: "+ año+ " Titulo: "+titulo;
        return resp;
    }
}
