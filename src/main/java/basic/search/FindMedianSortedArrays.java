package basic.search;

import org.junit.Test;

/**leetcode4 hard
 * https://www.nowcoder.com/discuss/196951
 * 寻找两个有序数组的中位数，要求时间复杂度O(log(m+n))
 * 将寻找中位数转化为寻找两个值
 * 中位数其实对于两个有序数组来说，我们只要找出第(m+n+1)/2大的数和第(m+n+2)/2大的数，然后求平均数即可
 */
public class FindMedianSortedArrays {
    @Test
    public void test() {
        System.out.println(solution(new int[]{1,2},new int[]{3,4}));
    }

    public double solution(int[] nums1,int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        //如果m+n是偶数，l和r分别是最中心的两个下标，如果m+n是奇数，l等于r
        int l=(m+n+1)/2;
        int r=(m+n+2)/2;
        System.out.println(getKth(nums1, 0, nums2, 0, l));
        System.out.println(getKth(nums1, 0, nums2, 0, r));
        return (getKth(nums1, 0, nums2, 0, l) + getKth(nums1, 0, nums2, 0, r))/2.0;
    }

    /**
     * 从两个有序数组中找到第K大的元素
     * @param nums1
     * @param start1
     * @param nums2
     * @param start2
     * @param k
     * @return
     */
    public int getKth(int[] nums1,int start1,int[] nums2,int start2,int k) {
        //边缘情况，其中一个数组中的所有数据都已经淘汰,则直接从另一个数组中去第K大的数
        if(start1>nums1.length-1) return nums2[start2+k-1];
        if(start2>nums2.length-1) return nums1[start1+k-1];
        //notice 不要忘记递归终止条件
        if(k==1) return Math.min(nums1[start1],nums2[start2]);
        //分别找数组的第k/2大的元素
        int nums1Mid=start1+k/2-1<nums1.length?nums1[start1+k/2-1]:Integer.MAX_VALUE;
        int nums2Mid=start2+k/2-1<nums2.length?nums2[start2+k/2-1]:Integer.MAX_VALUE;
        //如果数组1的中位数较小，则淘汰掉较小的这部分数据，
        if(nums1Mid<nums2Mid) {
            //已经排除了较小的这部分元素，所以我们要找的就是第k-k/2大的元素了
            return getKth(nums1,start1+k/2,nums2,start2,k-k/2);
        }else {
            return getKth(nums1,start1,nums2,start2+k/2,k-k/2);
        }
    }
}
