package simpledb;

import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.io.*;
//import java.util.*;

public class HeapFileIterator implements DbFileIterator {

    TransactionId tid;
    int pageCounter;
    int tableId;
    int numPages;
    Page page;
    Iterator<Tuple> tuples;
    HeapPageId pid;
    HeapFile hf;

    public HeapFileIterator(TransactionId tid, HeapFile hf) {
        this.tid = tid;
        this.hf = hf;
        this.tableId = hf.getId();
        this.numPages = hf.numPages();
        this.pageCounter = 0;
    }

    private Iterator<Tuple> getTuples(int pageNumber) throws DbException, TransactionAbortedException {
        pid = new HeapPageId(tableId, pageNumber);
        // HeapPageId id, byte[] data
        HeapPage heapPage = (HeapPage) Database.getBufferPool().getPage(tid, pid, Permissions.READ_ONLY);
        return heapPage.iterator();
    }

    public void open() throws DbException, TransactionAbortedException {
        pageCounter = 0;
        tuples = getTuples(pageCounter);
    }

    public boolean hasNext() throws DbException, TransactionAbortedException {
        if (tuples == null) return false;
        if (tuples.hasNext()) return true;
        if (pageCounter + 1 >= numPages) return false;
        while (pageCounter + 1 < numPages && !tuples.hasNext()) {
            pageCounter++;
            tuples = getTuples(pageCounter);
        }
        return this.hasNext();
    }

    public Tuple next() throws DbException, TransactionAbortedException, NoSuchElementException {
        if (tuples == null)
            throw new NoSuchElementException();
        return tuples.next();
    }

    public void rewind() throws DbException, TransactionAbortedException {
        close();
        open();
    }

    public void close() {
        tuples = null;
        pid = null;
    }

}
