void serveCruises(Cruise[] cruises) {
    ArrayList<Loader> loaderSchedule = new ArrayList<Loader>();

    for (Cruise cruise: cruises) {
        int numOfLoaders = cruise.getNumOfLoadersRequired();
        int counter = 0;

        while (numOfLoaders > 0) {
            for (; counter < loaderSchedule.size(); ++counter) {
                Loader checkingLoader = loaderSchedule.get(counter);
                boolean isThird = (counter + 1) % 3 == 0;

                if (checkingLoader.canServe(cruise)) {
                    checkingLoader = !isThird ?
                            new Loader(counter + 1, cruise) :
                            new RecycledLoader(counter + 1, cruise);

                    loaderSchedule.remove(counter);
                    loaderSchedule.add(counter, checkingLoader);
                    System.out.println(checkingLoader);
                    break;
                }
            }

            if (counter >= loaderSchedule.size()) {
                int loaderID = loaderSchedule.size() + 1;

                Loader newLoader = loaderID % 3 != 0 ?
                        new Loader(loaderID, cruise) :
                        new RecycledLoader((loaderID), cruise);

                System.out.println(newLoader);
                loaderSchedule.add(newLoader);
            }

            --numOfLoaders;
        }
    }
}
