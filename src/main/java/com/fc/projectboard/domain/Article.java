package com.fc.projectboard.domain; // 패키지 선언, 이 클래스가 속한 패키지의 경로를 지정함

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter // 모든 필드에 대한 getter 메서드를 자동으로 생성
@ToString(callSuper = true) // toString 메서드를 생성, 부모 클래스의 필드도 포함하여 출력
@Table(indexes = { // 이 클래스를 데이터베이스 테이블과 매핑하고 인덱스를 생성
        @Index(columnList = "title"), // 'title' 필드에 대한 인덱스 생성
        @Index(columnList = "hashtag"), // 'hashtag' 필드에 대한 인덱스 생성
        @Index(columnList = "createdAt"), // 'createdAt' 필드에 대한 인덱스 생성
        @Index(columnList = "createdBy") // 'createdBy' 필드에 대한 인덱스 생성
})
@Entity // 이 클래스가 데이터베이스 엔티티임을 선언, JPA가 관리
public class Article extends AuditingFields{ // AuditingFields 클래스를 상속 받는 Article 클래스 선언

    @Id // 이 필드가 엔티티의 기본 키임을 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 데이터베이스 IDENTITY로 지정(자동 증가)
    private Long id; // 기본 키 필드

    @Setter // setter 메서드를 자동 생성
    @ManyToOne(optional = false) // 다대일 관계를 나타내며, 반드시 관계가 존재해야 함(non-optional)
    @JoinColumn(name = "userId")
    private UserAccount userAccount; // 관계된 UserAccount 엔티티를 참조

    @Setter // 'title' 필드에 대한 setter 메서드 자동 생성
    @Column(nullable = false) // 데이터베이스 컬럼으로 매핑, null을 허용하지 않음
    private String title; // 글의 제목을 저장하는 필드

    @Setter // 'content' 필드에 대한 setter 메서드 자동 생성
    @Column(nullable = false, length = 10000) // 데이터베이스 컬럼으로 매핑, null을 허용하지 않고 최대 길이 10000
    private String content; // 글의 내용을 저장하는 필드

    @Setter // 'hashtag' 필드에 대한 setter 메서드 자동 생성
    private String hashtag; // 글에 포함된 해시태그를 저장하는 필드

    @OrderBy("createdAt DESC") // 'articleComments'를 'createdAt' 기준으로 내림차순 정렬
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // 일대다 관계, 'article' 필드에 의해 매핑되며, 연관된 엔티티도 모두 영속성 전이
    @ToString.Exclude // toString 메서드에서 이 필드 제외
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); // 관련된 댓글들을 저장하는 Set 컬렉션

    protected Article() { // 기본 생성자, 외부에서 생성을 제한함

    }

    private Article(UserAccount userAccount, String title, String content, String hashtag) { // 필드 초기화를 위한 생성자
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag) { // 객체를 생성하고 초기화하는 팩토리 메서드
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) { // 객체 동등성을 비교하는 메서드, 'id' 필드 기준으로 비교
        if (this == o) return true;
        if (!(o instanceof Article that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() { // 객체의 해시코드를 생성하는 메서드, 'id' 필드를 기반으로 해시코드 생성
        return Objects.hash(id);
    }
}
