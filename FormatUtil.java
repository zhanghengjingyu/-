package TaskSchdeuler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dengjingsi
 * 格式化工具类
 * S为需要作为主键的类型map的key类型（T中的一个属性类型），T为map的value值
 */
public class FormatUtil<S,T> {
    /**
     * 把list格式的SchoolRoom转化为Map格式
     *
     * @param schoolRoomList schoolRoom的数组
     * @return 返回schoolRoom的map集合格式
     */
    public static Map<Integer, SchoolRoom> formatSchoolRoomList(List<SchoolRoom> schoolRoomList) {
        if (null == schoolRoomList || schoolRoomList.size() == 0) {
            return new HashMap<>(0);
        }
        Map<Integer, SchoolRoom> schoolRoomMap = new HashMap<>(schoolRoomList.size());
        for (SchoolRoom schoolRoom : schoolRoomList) {
            schoolRoomMap.put(schoolRoom.getId(), schoolRoom);
        }
        return schoolRoomMap;
    }

    /**
     * 把list格式的对象以property方法名获取到的属性为主键，转化为map格式
     *
     * @param list
     * @param property
     * @return
     */
    public Map<S, T> formatList(List<T> list, String property) {
        if (null == list || list.size() == 0) {
            return new HashMap<>(0);
        }
        Map<S, T> map = new HashMap<>(list.size());
        try {
            for (T element : list) {
                Method method = element.getClass().getMethod(property, new Class[]{});
                System.out.println(method.invoke(element, new Object[]{}));
                map.put((S) method.invoke(element, new Object[]{}), element);
            }
        } catch (NoSuchMethodException n) {
            n.printStackTrace();
        } catch (InvocationTargetException it) {
            it.printStackTrace();
        } catch (IllegalAccessException i) {
            i.printStackTrace();
        }
        return map;
    }

    /**
     * 把list格式的对象以Id属性为主键，转化为map格式
     *
     * @param list
     * @return
     */
    public Map<S, T> formatList(List<T> list) {
        return formatList(list, "getId");
    }

    /**
     * 验证主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        //初始化验证基类begin
        List<SchoolRoom> schoolRooms = new ArrayList<>();
        SchoolRoom schoolRoom;
        for (int i = 0; i < 10; i++) {
            schoolRoom = new SchoolRoom();
            schoolRoom.setId(i);
            schoolRoom.setPlace("" + i);
            schoolRooms.add(schoolRoom);
        }
        //初始化验证基类end
        //通用方法
        FormatUtil<Integer, SchoolRoom> formatUtil = new FormatUtil<>();
        Map<Integer, SchoolRoom> map1 = formatUtil.formatList(schoolRooms);
        for (Map.Entry<Integer, SchoolRoom> entry : map1.entrySet()) {
            System.out.println("id:" + entry.getValue().getId() + ",place:" + entry.getValue().getPlace());
        }
        //普通方法
        System.out.println("-----------------------------");
        Map<Integer, SchoolRoom> map2 = FormatUtil.formatSchoolRoomList(schoolRooms);
        for (Map.Entry<Integer, SchoolRoom> entry : map2.entrySet()) {
            System.out.println("id:" + entry.getValue().getId() + ",place:" + entry.getValue().getPlace());
        }
    }
}