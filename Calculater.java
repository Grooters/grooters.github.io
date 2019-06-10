public class Calculater {

    public static void main(String...aegs){

        int[] values = { 1, 2, 9, 3, 12, 10, 4,0};

        Calculater calculater = new Calculater();

        calculater.quitSort( values, 0, values.length - 1 );

        System.out.print("快速排序：");

        for( int value : values ){

            System.out.print(" " + value);

        }

        Solution solution = new Solution();

        int result = solution.romanToInt("IVIVIIIXCD");

        System.out.print("\nresult："+ result);

        calculater.heepSort(values);

        System.out.print("\n堆排序：");

        for( int value : values ){

            System.out.print(" " + value);

        }

    }


    private void quitSort(int[] values, int l, int r){

        if( r > l){

            int i = l;

            int j = r;

            int key = values[l];

            while( j > i){

                while( j > i && values[j] >= key ){

                    --j;

                }

                if(j > i){

                    values[i] = values[j];

                    ++i;

                }

                while( j > i && values[i] <= key ){

                    ++i;

                }

                if( j > i ){

                    values[j] = values[i];

                    --j;

                }

            }

            quitSort( values, l, i - 1);

            quitSort( values, j + 1, r);

        }

    }


    private void arrangeTree(int[] tree, int n, int i){

        if( i >= n ){

            return;

        }

        int max = i;

        int leaf1 = max * 2 + 1;

        int leaf2 = max * 2 + 2;

        if( leaf1 < n && tree[max] < tree[leaf1]){

            max = leaf1;

        }

        if( leaf2 < n && tree[max] < tree[leaf2] ){

            max = leaf2;

        }

        if( max != i){

            swap(tree, i, max);

            arrangeTree(tree, n, max);

        }

    }

    private void buildHeep(int[] tree){

        int n = tree.length;

        int parent = ( ( n -1 ) - 1 ) / 2;

        for( int i = parent; i >= 0; i--){

            arrangeTree(tree, n, i);

        }

    }

    private void heepSort(int[] tree){

        int n = tree.length;

        buildHeep(tree);

        // 将第一个节点与最后一个节点交换，并砍断（i--）
        for( int i = n - 1; i >= 0; i-- ){

            swap(tree, i, 0);

            arrangeTree( tree, i, 0 );

        }

    }

    private void swap(int[] tree, int i, int max){

        int temp = tree[i];

        tree[i] = tree[max];

        tree[max] = temp;

    }

}

class Solution {

    int romanToInt(String s) {

        char c, n;

        int result = 0;

        for( int i = 0; i < s.length(); i++ ){

            c = s.charAt(i);

            if( i < s.length() - 1 ){

                n = s.charAt(i + 1);

            }else{

                n = ' ';

            }

            if( c == 'I'){

                if( n != ' ' && n == 'V'){

                    result = result + 4;

                    ++i;

                }else if( n != ' ' && n == 'X' ){

                    result = result + 9;

                    ++i;

                }else{

                    result = result + 1;

                }

            }else if( c == 'X'){

                if( n != ' ' && n == 'L' ){

                    result = result + 40;

                    ++i;

                }else if( n != ' ' && n == 'C' ){

                    result = result + 90;

                    ++i;

                }else{

                    result = result + 10;

                }


            }else if( c == 'C'){

                if( n != ' ' && n == 'D' ){

                    result = result + 400;

                    ++i;

                }else if( n != ' ' && n == 'M' ){

                    result = result + 900;

                    ++i;

                }else{

                    result = result + 100;

                }

            }

        }

        return result;

    }

}