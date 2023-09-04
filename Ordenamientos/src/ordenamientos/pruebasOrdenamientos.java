
package ordenamientos;
import java.util.Scanner;
import java.io.File;
public class pruebasOrdenamientos  <T extends Comparable <T>>{
    static int p1=0, p2=0, p3=0, p4=0;
    
    public static void main(String[] args) {
        String nombreAr, nombre;
        File archivo;
        Scanner leer;
        Pelicula p;
        pruebasOrdenamientos d= new pruebasOrdenamientos();
        int i=0;
        Pelicula [] peliculas= new Pelicula[100];
        
        archivo= new File("movie_titles2.txt");
        try
        {
            String[] arr= new String[3];
            leer= new Scanner(archivo);
            while(leer.hasNextLine() && i<peliculas.length)
            {
                arr= leer.nextLine().split(",",3);
                p= new Pelicula(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), arr[2]);
                peliculas[i]=p;
                i++;
            }
            leer.close();
        }catch(Exception error)
        {
            System.out.println(error);
        }
        
        d.ordMS(peliculas);
        d.imprimir(peliculas);
 /*       long t1= System.nanoTime();
        d.ordIS(peliculas);
        long t2= System.nanoTime();
        System.out.println("-------------------------------");
        d.imprimir(peliculas);
        System.out.println("Pasos: "+p2);
        System.out.println("Tiempo: "+(t2-t1));
        
        System.out.println("------------------------------");
        d.ordInverso(peliculas);
        t1= System.nanoTime();
        d.ordIS(peliculas);
        t2= System.nanoTime();
        System.out.println("Pasos: "+p2);
        System.out.println("Tiempo: "+(t2-t1));
        
        System.out.println("----------------------------");
        d.ordAleatorio(peliculas,peliculas.length-1);
        t1= System.nanoTime();
        d.ordIS(peliculas);
        t2= System.nanoTime();
        System.out.println("Pasos: "+p2);
        System.out.println("Tiempo: "+(t2-t1));
        */
    }
    //----------------------------------------------------------    
    public void ordSD(T[] a)
    {
        p1=0;
    	int min;
    	for(int i=0; i<a.length;i++)
    	{
            min =i;
            for(int j= i+1; j<a.length;j++)
            {
                if(a[j].compareTo(a[min])<0)
                {
                    min= j;
                }
                p1=p1+2;
            }
            swap(a,i,min);
            p1++;
    	}
    }
    
    public void ordIS(T [] a)
    {
    	int n= a.length;
    	for(int i=1; i<n;i++)
    	{
            T aux=a[i];
            int j= i-1;
            p2++;
            while(j>=0 && a[j].compareTo(aux)>0)
            {
        	a[j+1]= a[j];
        	j=j-1;
                p2=p2+1;
            }
            a[j+1]= aux;
    	}
    }
    
    //bubble
    public void ordBS(T[] arr) {  
        int n = arr.length;  
        int temp = 0;  
        for(int i=0; i < n; i++)
        {  
            for(int j=1; j < (n-i); j++)
            {  
                if(arr[j-1].compareTo(arr[j])>0)
                {
                    swap(arr, j,j-1);
                }     
            }  
        }
    }

    //quickSort
    public void ordQS(T[] arr, int ini, int fin)
    {
	    if(ini<fin)
	    {
		    int piv= particion(arr,ini,fin-1);
		    ordQS(arr,ini,piv);
		    ordQS(arr,piv+1,fin);
	    }
    }
    
    public int particion(T[] arr, int ini, int fin)
{
    while(ini<fin)
    {
        if(arr[ini+1].compareTo(arr[ini]) < 0)
        {
            swap(arr,ini,ini+1);
            ini++;
        }else
        {
            swap(arr, ini+1, fin);
            fin--;
        }
    }
    return ini;
}
		
/*
	private int Particion(T[] arr, int min, int max)
	{
 		int fin= max-1;
   		int res= min;
     		int aux= min+1;

	 	while(aux<=fin)
   		{
     			if(arr[aux]>arr[res]{
			swap(arr,aux,fin);
   			fin--;
      			}
	 		else{
				/wap(arr,res,aux);
    				res=aux;
				aux++;
    		}
      		return res;
       	   }
*/

  public void ordMS(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        T[] temp = (T[]) new Comparable[arr.length];
        mezclar(arr, temp, 0, arr.length - 1);
    }
  
   private void mezclar(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mezclar(arr, temp, left, middle);
            mezclar(arr, temp, middle + 1, right);

            int i = left;
            int j = middle + 1;
            int k = left;

            while (i <= middle && j <= right) {
                if (arr[i].compareTo(arr[j]) <= 0) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }

            while (i <= middle) {
                temp[k++] = arr[i++];
            }

            while (j <= right) {
                temp[k++] = arr[j++];
            }

            for (k = left; k <= right; k++) {
                arr[k] = temp[k];
            }
        }
    }

/*

 public void ordMS(T[] datos, int ini, int fin)
    {
        if(ini>=fin)
        {
            return;
        }
        
        int mitad= (int) Math.floor((ini+fin)/2);
        ordMS(datos,ini,mitad);
        ordMS(datos,mitad+1,fin);
        mezclar(datos,ini,fin,mitad);
    }
  
    public void mezclar(T[] a, int ini, int fin, int mitad)
    {
        int med= mitad;
        int izq=ini; int der= med+1;int ia=0;
        T[] aux= a;
        while(izq<=med && der<=fin)
        {
            if(a[ia].compareTo(a[der])<0)
            {
                aux[ia]=a[izq];
                izq++;
            }
            else
            {
                aux[ia]= a[der];
                der++;
            }
            ia++;
        }
        
        for(int k=der; k<fin;k++)
        {
            aux[ia]= a[k];
            ia++;
        }
        for(int k=izq; k<med;k++)
        {
            aux[ia]= a[k];
            ia++;
        }
        for(int k=0; k<ia-1;k++)
        {
            a[ini+k]= aux[k];
        }
    }
 */
  
  
    public void ordAleatorio(T[] a, int tam)
    {
        if(tam==0)
        {
            return;
        }
        int c = (int) (Math.random() * tam);
        swap(a,c, tam-1);
        ordAleatorio(a,tam-1);
    }
    
    public void ordInverso(T[] a)
    {
        ArrayStack <T> pila= new ArrayStack(a.length);

        for(int i=0;i<a.length;i++)
        {
            pila.push(a[i]);
            a[i]=null;
        }
        
        for(int i=0;i<a.length;i++)
        {
            a[i]= pila.pop();
        }
    }

public void swap(T[] a, int i1, int i2)
{
    T aux= a[i1];
    a[i1]= a[i2];
    a[i2]= aux;
}


public void imprimir(T[] a)
{
    for (T a1 : a) {
        System.out.println(a1.toString());
    }
}
    
}
