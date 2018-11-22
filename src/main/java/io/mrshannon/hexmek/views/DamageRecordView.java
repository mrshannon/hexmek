package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.DamageRecord;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Damage record view to display damage dealt messages and component destroyed messages.
 */
public class DamageRecordView implements View {

    private ArrayList<DamageRecord> records;

    /**
     * Create damage record view.
     *
     * @param records records to print
     */
    public DamageRecordView(Collection<DamageRecord> records) {
        this.records = new ArrayList<>(records);
    }

    /**
     * Render the damage records.
     */
    @Override
    public void render() {
        for (var record : records) {
            System.out.println(record);
        }
    }

}
