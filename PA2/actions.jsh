Function<Room, Room> takeSword = r -> {
    if (!r.t().stream().anyMatch(o -> o instanceof Sword)) {
        System.out.println("--> There is no sword.");

        return r;
    }

    System.out.println(r.s() > 0 ?
            "--> You already have sword." :
            "--> You have taken sword.");

    return new Room(r.l(), r.t(), r.i(), 1, r.r());
}

Function<Room, Room> killTroll = r -> {
    if (r.s() > 0) {
        System.out.println("--> Troll is killed.");
        r.t().removeIf(x -> x instanceof Troll);

        return new Room(r.l(), r.t(), r.i(), 1, r.r());
    }

    if (!r.t().stream().anyMatch(o -> o instanceof Troll)) {
        System.out.println("--> There is no troll");

        return r;
    }

    System.out.println("--> You have no sword.");

    return r;
}

Function<Room, Room> dropSword = r -> {
    System.out.println("--> You have dropped sword.");

    return new Room(r.l(), r.t(), r.i(), 0, r.r());
}
