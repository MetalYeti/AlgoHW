package HW2;

public class Main {
    public static void main(String[] args) {

        Notebook[] array = NotebookFactory.generateArray(10000);

        sortInsertObj(array);

        for (Notebook n : array){
            System.out.println(n);
        }

    }

    public static void sortInsertObj(Notebook[] arr){
        int in, out;
        for(out = 1;out < arr.length; out++){
            Notebook temp = arr[out];
            in = out - 1;
            while(in >= 0 && arr[in].compareTo(temp) > 0){
                arr[in + 1] = arr[in];
                in--;
            }
            arr[in + 1] = temp;
        }
    }

}
