import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException {

        Client client=new Client();
        Scanner in=new Scanner(System.in);
        System.out.println("fhdfh");
        in.nextLine();

        while(true){
            switch (in.nextLine()){

                case "con":

                    System.out.print(ANSI_GREEN+"server:  "+ANSI_RESET);
                    String server=in.nextLine();

                    System.out.print(ANSI_GREEN+"port:    "+ANSI_RESET);
                    int port=Integer.parseInt(in.nextLine());

                    client.connect("localhost",21);
                    break;

                case "login":

                    System.out.print(ANSI_GREEN+"username:   "+ANSI_RESET);
                    String user=in.nextLine();

                    System.out.print(ANSI_GREEN+"password:   "+ANSI_RESET);
                    String pas=in.nextLine();

                    client.login(user,pas);

                    break;

                case "download":
                {
                    System.out.print(ANSI_GREEN + "Имя скачиваемого файла:  " + ANSI_RESET);
                    String oldfilename = in.nextLine();

                    System.out.print(ANSI_GREEN + "Название для сохранения:    " + ANSI_RESET);
                    String newfilename = in.nextLine();

                    System.out.println(client.download(oldfilename, newfilename));

                    break;
                }
                case "upload": {
                    System.out.print(ANSI_GREEN + "Имя файла загружаемого на сервер:  " + ANSI_RESET);
                    String oldfilename = in.nextLine();

                    System.out.print(ANSI_GREEN + "Название для сохранения:    " + ANSI_RESET);
                    String newfilename = in.nextLine();

                    System.out.println(client.upload(oldfilename, newfilename));

                    break;
                }
                case "del":
                    System.out.print(ANSI_GREEN+"Название удаляемого файла:  "+ANSI_RESET);
                    String delname=in.nextLine();
                    System.out.println("Операция удаления файла завершилась с кодом " +client.delete(delname));
                    break;
                case "ren":{
                    System.out.print(ANSI_GREEN+"oldname:  "+ANSI_RESET);
                    String oldname=in.nextLine();

                    System.out.print(ANSI_GREEN+"newname:    "+ANSI_RESET);
                    String newname=in.nextLine();

                    System.out.println("Операция переименования файла завершилась с кодом " +
                            client.rename(oldname,newname));
                    break;}
                case "watch":
                    client.server_content();
                    break;


            }
        }

    }
}
