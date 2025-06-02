package hello.core.singleton;


public class SingletonService {

    // private 으로 객체를 1개 생성, static 영역에 올림
    private static final SingletonService instance = new SingletonService();

    // instance 에 접근 할 수 있는 메서드
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private 로 막음
    private SingletonService(){
        
    }
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
    
}
