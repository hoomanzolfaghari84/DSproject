package Huffman;

import Huffman.exceptions.HuffmanAlgorithmException;
import Huffman.exceptions.HuffmanHeapException;
import Huffman.trees.HuffmanHeap;
import Huffman.trees.HuffmanTree;
import Huffman.trees.Leaf;
import Huffman.trees.Node;

public class HuffmanAlgorithm {

    public HuffmanCompressed compressText(String text){
        HuffmanMap huffmanMap = findFrequencies(text);
        HuffmanTree huffmanTree = makeTree(huffmanMap);
        findCodes(huffmanMap,huffmanTree);


        StringBuilder compressedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            compressedText.append(huffmanMap.getCode(text.charAt(i)));
        }


        return new HuffmanCompressed(huffmanTree,compressedText.toString());
    }

    private void findCodes(HuffmanMap huffmanMap,HuffmanTree huffmanTree){
        recursiveCodeFinderByArray("",huffmanTree.getRoot(),huffmanMap);
    }


    private void recursiveCodeFinderByArray(String code, Node node,HuffmanMap huffmanMap){
        if (node == null) return;

        if(node instanceof Leaf){
            Leaf leaf = (Leaf) node;
            huffmanMap.put(leaf.getCharacter(),code);
        }

        recursiveCodeFinderByArray(code+"0",node.getLeftChild(),huffmanMap);
        recursiveCodeFinderByArray(code+"1",node.getRightChild(),huffmanMap);

    }

    private HuffmanMap findFrequencies(String text){
        HuffmanMap huffmanMap = new HuffmanMap();
        for (char character: text.toCharArray()) {
            huffmanMap.add(character);
        }
        return huffmanMap;
    }


    private HuffmanTree makeTree(HuffmanMap huffmanMap){

        HuffmanHeap huffmanHeap = new HuffmanHeap(huffmanMap);

        while (huffmanHeap.getSize()>1){
            Node left = huffmanHeap.pop();
            Node right = huffmanHeap.pop();

            Node node = new Node(left.getFrequency()+right.getFrequency());
            node.setLeftChild(left);
            node.setRightChild(right);

            huffmanHeap.push(node);

        }

        return new HuffmanTree(huffmanHeap.pop());
    }


    // returns the text extracted from compressed text which was compressed by huffman algorithm and consists of bits and a tree
    public String extractText(HuffmanCompressed huffmanCompressed){

        return null;
    }


}

