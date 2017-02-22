import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] sort = new int[]{13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        buildMaxHeapify(sort);
        heapSort(sort);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 创建堆
     *
     * @param data
     */
    static void buildMaxHeapify(int[] data) {
        //没有子节点的才需要创建最大堆，从最后一个的父节点开始
        int startIndex = getParentIndex(data.length - 1);
        //从尾端开始创建最大堆，每次都是正确的堆
        for (int i = startIndex; i >= 0; i--) {
            maxHeapify(data, data.length, i);
        }
    }

    /**
     * 创建最大堆
     *
     * @param data     堆数组
     * @param heapSize 需要创建最大堆的大小，一般在sort的时候用到，因为最多值放在末尾，末尾就不再归入最大堆了
     * @param index    当前需要创建最大堆的位置
     */
    static void maxHeapify(int[] data, int heapSize, int index) {
        //当前点与左右子节点比较
        int left = getChildLeftIndex(index);
        int right = getChildRightIndex(index);
        //记录最大节点位置
        int largest = index;
        //如果当前节点小于左节点，最大值则为左节点
        if (left < heapSize && data[index] < data[left]) {
            largest = left;
        }
        //如果当前节点小于右节点，最大值则为右节点
        if (right < heapSize && data[largest] < data[right]) {
            largest = right;
        }
        //得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
        if (largest != index) {
            int temp = data[index];
            data[index] = data[largest];
            data[largest] = temp;
            maxHeapify(data, heapSize, largest);
        }
    }

    /**
     * 排序
     *
     * @param data
     */
    static void heapSort(int[] data) {
        //末尾与头交换，交换后调整最大堆
        for (int i = data.length - 1; i > 0; i--) {
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            maxHeapify(data, i, 0);
        }
    }

    /**
     * 父节点位置
     *
     * @param current 当前节点位置
     * @return
     */
    static int getParentIndex(int current) {
        return (current - 1) >> 1;
    }

    /**
     * 左子节点位置，注意括号，加法优先级更高
     *
     * @param current 当前节点位置
     * @return
     */
    static int getChildLeftIndex(int current) {
        return (current << 1) + 1;
    }

    /**
     * 右子节点位置
     *
     * @param current 当前节点位置
     * @return
     */
    static int getChildRightIndex(int current) {
        return (current << 1) + 2;
    }
}
