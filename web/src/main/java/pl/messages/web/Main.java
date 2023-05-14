package pl.messages.web;

//public class Main {
//    public static void main(String[] args) {
//        int a = 500; // money
//        int b = 2; // procent
//        int c = 5; // month amount
//        int res;
//        for (int i = 0; i < c; i++) {
//            res = a * b / 100;
//            a += res;
//            System.out.println(a);
//        }
//    }
//
//    class Main {
//
//        public static void main(String args[]) {
//            //Scanner in = new Scanner(System.in);
//            String chars = "abcdefghijklmnopq";
//            String s = "Hello, world";
//            String toGet = "d";
//            String a;
//            char b = 0;
//
//            for(int i = 0; i < chars.length(); i++) {
//                a = chars.valueOf(i);
//                if(a == toGet) {
//                    for (int j = 0; j < i; j++) {
//                        if (j == i) {
//                            System.out.println("" + b = s.charAt(j));
//                        }
//                    }
//                }
//            }
//
//            for (int i = 0; i < s.length(); i++) {
//                System.out.println(s.charAt(i));
//            }
//        }
//    }

//

//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
//    }
//
//    public static int[] twoSum(int[] nums, int target) {
//        int[] res = new int[2];
//        for(int i = 0; i < nums.length; i++) {
//            if(nums[i] < target) {
//                res[0] = i;
//            }
//        }
//        for(int i = 0; i < nums.length; i++) {
//            if (nums[i] == res[0] - target) {
//                res[1] = i;
//            }
//        }
//        return res;
//    }
//}