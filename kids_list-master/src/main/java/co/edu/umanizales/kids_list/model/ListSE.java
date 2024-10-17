package co.edu.umanizales.kids_list.model;

import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;
    private ListSE listCP;

    public void add(Kid kid){
        if(head == null){
            head = new Node(kid);
        }
        else{
            Node temp = head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new Node(kid));
        }
        size++;
    }

    public void addToStart(Kid kid){
        if(head == null){
            head = new Node(kid);
        }
        else{
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }
    public void addInPosition(Kid Kid, int position){
        if(head == null || position == 1){
            addToStart(Kid);
        }
        else{
            if(position > size ){
                add(Kid);
            }
            else{
                int pos = 1;
                Node temp = head;
                while(pos < position-1) {
                    temp = temp.getNext();
                    pos++;
                }
                //position before
                Node newnodo= new Node(Kid);
                newnodo.setNext(temp.getNext());
                temp.setNext(newnodo);
                size++;
            }
        }

    }
    // Función para invertir la lista
    public void invert() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node nodoanterior = null;
        Node nodoactual = head;
        Node temp = null;

        while (nodoactual != null) {
            temp = nodoactual.getNext();// Guarda la referencia del siguiente nodo
            nodoactual.setNext(nodoanterior);// Cambia la dirección del nodo actual al nodo anterior
            nodoanterior = nodoactual;// Avanza nodoanterio al nodo actual
            nodoactual = temp; // Avanza el nodoactual al siguiente nodo
        }

        head = nodoanterior; // Actualiza la cabeza de la lista
    }
    // Función para borrar un niño por ID
    public void deleteById(String id) {
        if (head == null) {
            return;
        }
        //Aca miro si el niño que quiero eliminar es el primero
        if (head.getData().getId().equals(id)) {
            head = head.getNext(); // Si el nodo a eliminar es el primero
            size--;
            return;
        }

        Node temp = head;
        while (temp.getNext() != null && !temp.getNext().getData().getId().equals(id)) {
            temp = temp.getNext();
        }

        if (temp.getNext() != null) {
            temp.setNext(temp.getNext().getNext()); // Elimina el nodo con el ID
            size--;
        }

    }
    // Función para borrar un nodo por posición
    public void deleteByPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }

        if (position == 1) {
            head = head.getNext();
        } else {
            Node temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext(); // Variable temp Encuentra el nodo anterior al que se va a eliminar
            }
            temp.setNext(temp.getNext().getNext()); //salta al nodo anterior
        }
        size--;
    }
    // Intercalar niños por género
    public void intercalarxGenero() {
        if(size > 2){
            int posB=1;
            int posG=2;
            ListSE listSE = new ListSE();
            Node temp = head;
            while(temp != null){
                switch(temp.getData().getGender()){
                    case 'F':
                    case 'f':
                        listCP.addInPosition(temp.getData(), posG);
                        posG+= 2;
                        break;
                    default:
                        listCP.addInPosition(temp.getData(), posG);
                        posG+= 2;

                }
                temp = temp.getNext();
            }
        }

    }



    public void intercambiarExtremos() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node temp = head;
        Node anterior = null;
        Node ultimo = head;

        while (ultimo.getNext() != null) {
            anterior = ultimo;
            ultimo = ultimo.getNext();
        }

        if (anterior != null) {
            anterior.setNext(head);
            Node first = head.getNext();
            ultimo.setNext(first);
            head.setNext(null);
            head = ultimo;
        }
    }

}