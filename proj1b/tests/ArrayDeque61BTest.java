import deque.ArrayDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }
    @Test
    public void addFirstTest(){
         ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
         list.addFirst(3);
         list.addFirst(4);

         assertThat(list.get(0)).isEqualTo(4);
         assertThat(list.size()).isEqualTo(2);
    }


    @Test
    public void addLastTest() {
         ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
         list.addFirst(3);
         list.addLast(4);
         list.addLast(6);

         assertThat(list.get(list.size() -1)).isEqualTo(6);
         assertThat(list.get(list.size() - 2)).isEqualTo(4);
         assertThat(list.size()).isEqualTo(3);
    }


    @Test
    public void isEmptyTest() {
         ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
         ArrayDeque61B<Integer> list1 = new ArrayDeque61B<>();
         list.addFirst(3);
         list.addLast(4);
         assertThat(list.isEmpty()).isFalse();
         assertThat(list1.isEmpty()).isTrue();

    }


    @Test
    public void sizeTest() {
         ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
         ArrayDeque61B<Integer> list1 = new ArrayDeque61B<>();
         list.addFirst(3);
         list.addLast(4);
         assertThat(list.size()).isEqualTo(2);
         assertThat(list1.size()).isEqualTo(0);

    }

    @Test
    public void toListTest() {
         ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
         list.addFirst(3);
         list.addLast(4);
         assertThat(list.toList()).containsExactly(3,4);
    }
}
