import java.io.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;

public class Student{
   String student_path = "students.txt";

   public void modify_Cgpa(String id,String newCgpa) throws IOException{

      RandomAccessFile std_file = new RandomAccessFile(student_path,"rw");
      boolean found = false;

      byte[] idno = new byte[4];
      String idStr = null;
      byte[] rec = new byte [53];
      String recStr = null;

      while(std_file.getFilePointer() <= std_file.length()-2){
         std_file.read(idno,0,4);
         idStr = new String(idno);

         if(idStr.equals(id)){
            found = true;
            std_file.seek(std_file.getFilePointer()-4);
            std_file.seek(std_file.getFilePointer()+36);
            std_file.write(newCgpa.getBytes(StandardCharsets.UTF_8));
         }
            if(found)
               break;

         std_file.seek(std_file.getFilePointer() + 51);

      }
   std_file.close();
   }
   public void display_Student(String id) throws IOException{

      RandomAccessFile std_file = new RandomAccessFile(student_path,"rw");
      boolean found = false;

      byte[] idno = new byte[4];
      byte[] rec = new byte[53];
      String idStr;
      String std_rec = null;
      String std_fields[] = null;
      String std_line = null;

      while(std_file.getFilePointer() <= std_file.length()-2){

         std_file.read(idno,0,4);
         idStr = new String(idno);

         if(idStr.equals(id)){
            std_file.seek(std_file.getFilePointer()-4);
            std_file.read(rec,0,53);
            std_rec = new String(rec);
            std_fields = std_rec.split(",");
            found = true;
         }
         if(found)
            break;
         std_file.seek(std_file.getFilePointer()+51);
      }
      System.out.println(std_rec);
      System.out.println("ID : "+std_fields[0]);
      System.out.println("NAME : "+std_fields[1]);
      System.out.println("CGPA : "+std_fields[2]);
      System.out.println("DOB : "+std_fields[3]);
      System.out.println("GENDER : "+std_fields[4]);



      std_file.close();
   }

}
