import java.io.*;
import java.util.ArrayList;

public class PR2AVAEX02_Ivan_Cabellos {

    //Fichero principal a convertir, se encarga de darle el valor del fichero
    private static InputStream inputStream = devuelveFichero();
    //Array de bytes donde se guardan los valores individuales del fichero
    private static byte[] bytesFicheroArray = meteFicheroEnArray();

    /*
    Metodo para determinar el fichero del atributo de la clase, asi podemos modificarlos
    y este metodo devolverá un InputStream el cual asignara
     */
    public static InputStream devuelveFichero(){

        try {
            //Aqui podemos cambiar el nombre del fichero
            inputStream = new DataInputStream(new FileInputStream("PR2AVAEX02.mp3"));

            return inputStream;

        } catch (IOException e){
            System.err.println();
        }

        return null;
    }

    /*
    Esta funcion de la clase permite meter cada uno de los bytes del fichero en un array de bytes que hemos declarado
    en la clase que determinan cada posicion de esta. Lo usaremos para luego ir modificando cada posicion de los bytes.
     */
    public static byte[] meteFicheroEnArray (){

        try {

            //Asignamos un valor a la variable de los bytes del fichero
            byte bytFichero = (byte)inputStream.read();

            ArrayList<Byte> bytesFichero = new ArrayList<>();

            while (bytFichero != -1){

                //Lo vamos metiendo dentro de la lista
                bytesFichero.add(bytFichero);

                bytFichero = (byte)inputStream.read();
            }

            bytesFicheroArray = new byte[bytesFichero.size()];

            for (int i = 0; i < bytesFichero.size(); i++) {

                //Metemos los valores de la lista dentro del array que hemos instanciado como statica dentro de la clase
                bytesFicheroArray[i] = bytesFichero.get(i);

            }

            //System.out.println(Arrays.toString(bytesFicheroArray));
            return bytesFicheroArray;

        } catch (IOException e){
            System.err.println();
        }

        return null;

    }


    /*Esta es la funcion principal, se basa en tener dos variables, inicio del byte del archivo donde queremos modificar
     * y hasta donde se modificará, tendremos que tener en cuenta la medida del archivo y donde debe terminar.
     */
    public static void modificaBytes (short posCambiarTexto, String fraseAModificar){

        //Creamos una lista para introducir los bytes que tenemos que modificar del fichero
        ArrayList<Byte> bytesACambiar = new ArrayList<>();

        //Con esto meteremos los carácteres del String dentro de la lista
        for (int i = 0; i < fraseAModificar.length(); i++) {
            byte byteAMeter = (byte)fraseAModificar.charAt(i);
            bytesACambiar.add(byteAMeter);
        }

        // System.out.println(bytesACambiar.toString());
        // [69, 120, 97, 109, 101, 110, 32, 112, 114, 111, 103, 114, 97, 109, 97, 99, 105, -13, 32, 67, 73, 68, 69]
        // = Examen Programació CIDE

        //Contador para la lista
        short index = 0;

        for (int i = posCambiarTexto; i < (posCambiarTexto + bytesACambiar.size()); i++) {

            //Introducimos los valores de la lista dentro de la array
            bytesFicheroArray[i] = bytesACambiar.get(index);
            index++;

        }


    }

    /*
    Con esto hacemos una copia del fichero que tengamos instanciado en la clase dentro de la array de la clase
    */
    public static OutputStream copiaFichero (){

        try {
            //Fichero copia que debe ser modificado
            OutputStream outputStream = new DataOutputStream(new FileOutputStream("PR2AVAEX02_Ivan_Cabellos.mp3"));

            for (int i = 0; i < bytesFicheroArray.length; i++) {
                outputStream.write(bytesFicheroArray[i]);
            }

        } catch (IOException e){
            System.err.println();
        }

        return null;
    }

    //MAIN
    public static void main(String[] args) {

        /*
        * Una vez que las varibles de la clase estan inicializadas debemos de modificarlas, por lo que acordando donde
        * se encuentran las posiciones de los bytes a modificar, introducimos donde empieza, y seguido la frase que
        * meter dentro del archivo
        * */
        modificaBytes((short) 32, "Examen programació CIDE");

        /*
        * Finalmente, una vez modificado llamaremos a la función que se encarga de copiar el archivo que tenemos, así
        * que al copiar el archivo que previamente hemos modificado sacaremos el archivo necesario.
        * */
        copiaFichero();

        /*
        *
        * Báscicamente como se estructura el programa es que primero inicializamos las variables del fichero que
        * guardamos y a posteriori inicializamos un array de bytes para ir guardandolos, una vez hecho esto tendremos
        * que modificar dichos bytes y su localización de estas. Por lo que si sabemos donde empieza el
        * texto dentro del archivo MP3 podremos ir cambiando byte a byte hasta que termine dicha modificacion.
        * Una vez modificado cada byte del archivo toca hacer la copia de este, así que llamamos a la funcion
        * copiaArchivo que se dedicará a ello.
        *
        * */

    }


}
