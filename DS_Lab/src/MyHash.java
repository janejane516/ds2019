public class MyHash {
    int data[];
    boolean full[];
    int size;

    public MyHash(int size) {
        this.size = size;
        data = new int[size];
        full = new boolean[size];
    }

    public void add(int value) {
        int where = value % size;
        if(!full[where]) {
            data[where] = value;
            full[where] = true;
        }
        else {
            while (true) {
                where++;
                if(where == 17) {
                    where = 0;
                }
                if(!full[where]) {
                    data[where] = value;
                    full[where] = true;
                    break;
                }
            }
        }
    }

    public void remove(int value) {
        int where = value % size;
        int start = where + 1;
        if(start >= size) {
            start = 0;
        }
        int endpoint = where; //for the case that the elem doesn't exist
        while (true) {
            if(data[where] == value) {
                full[where] = false;
                break;
            }
            where++;
            if(where == 17) {
                where = 0;
            }
            if(endpoint == where) {
                return;
            }
        }
        int se = start;

        while(true) {
            int k = start + 1;
            int ek = start;
            while(true) {
                if(!full[start] && (data[k]%size) == start) {
                    full[start] = true;
                    data[start] = data[k];
                    break;
                }
                k++;
                if(k == 17)
                    k = 0;
                if(k == ek)
                    break;
            }
            start++;
            if(start == 17) {
                start = 0;
            }
            if(start == se) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        String ret = "";
        for(int i = 0; i < size; i++) {
            if(full[i]) {
                ret = ret.concat(String.valueOf(data[i]) + " ");
            }
        }
        return ret;
    }

    public String toString2() {
        String ret = "";
        for(int i = 0; i < size; i++) {
            if(full[i]) {
                ret = ret.concat("1 ");
            }
            else {
                ret = ret.concat("0 ");
            }
        }
        return ret;
    }
}
