public class Main {

    public static void main(String[] args) throws Exception {
        BST<Integer, String> bst = new BST<>();
        bst.put(5, "Rasul Eletai");
        bst.put(1, "Dilnaz Alimbayeva");
        bst.put(15, "Nurdaulet Orynbassarov");
        bst.put(2, "Rulan Alimkhan");
        bst.put(6, "Ersin Gabbas");
        bst.put(8, "Olzhas Imangeldin");
        bst.put(14, "Madi Mukash");
        bst.put(7, "Ernazar Iakupov");
        bst.put(9, "Yerzhan Kabdolla");
        bst.put(10, "Adel Kalieva");
        bst.put(20, "Alibek Zhampeisov");
        bst.put(18, "Aisultan Tabuldin");
        bst.put(11, "Sabina Kassymova");
        bst.put(12, "Aruzhan Kundak");
        bst.put(16, "Rakhat Ospanov");
        bst.put(13, "Ansar Kussainov");
        bst.put(3, "Sultan Aubakirov");
        bst.put(17, "Daniial Saden");
        bst.put(4, "Dayana Belyalova");
        bst.put(19, "Aliaskar Uteshkaliev");

        for (var elem : bst) {
            System.out.println(elem);
        }
        System.out.println();
        System.out.println(bst.get(10));
        System.out.println(bst.get(18));
        System.out.println();
        bst.delete(2);
        bst.inOrderREC();
    }

}