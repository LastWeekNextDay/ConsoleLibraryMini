package dev.lwnd.member;

import java.util.ArrayList;
import java.util.List;

import dev.lwnd.Library;

/**
 * The MemberCollectionBuilder class is responsible for building a MemberCollection object.
 * It provides methods to add individual members or a list of members to the collection,
 * and a build method to finalize the construction of the MemberCollection.
 */
public class MemberCollectionBuilder {
    private final List<Member> members;
    private final MemberCollection memberCollection;

    /**
     * Constructs a new MemberCollectionBuilder object with the specified Library object.
     *
     * @param library the Library object to associate with the MemberCollection
     */
    public MemberCollectionBuilder(Library library) {
        this.members = new ArrayList<>();
        memberCollection = new MemberCollection(library);
    }

    /**
     * Adds a member to the collection.
     *
     * @param member the Member object to add
     * @return the MemberCollectionBuilder object for method chaining
     */
    public MemberCollectionBuilder addMember(Member member) {
        this.members.add(member);
        return this;
    }

    /**
     * Adds a list of members to the collection.
     *
     * @param members the list of Member objects to add
     * @return the MemberCollectionBuilder object for method chaining
     */
    public MemberCollectionBuilder addMembers(List<Member> members) {
        this.members.addAll(members);
        return this;
    }

    /**
     * Builds and returns the final MemberCollection object.
     * The added members are added to the MemberCollection.
     *
     * @return the built MemberCollection object
     */
    public MemberCollection build() {
        memberCollection.addMembers(this.members);
        return memberCollection;
    }
}
