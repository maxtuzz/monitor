
select partition_init(date '2019-01-01', 'week', 'metric_entry', 'id', 'event_time', 5);
select partition_init(date '2019-01-01', 'week', 'metric_rollup_m10', 'id', 'event_time', 5);

