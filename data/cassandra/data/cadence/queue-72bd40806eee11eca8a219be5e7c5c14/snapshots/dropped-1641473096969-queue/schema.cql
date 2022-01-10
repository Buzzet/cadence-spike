CREATE TABLE IF NOT EXISTS cadence.queue (
	queue_type int,
	message_id int,
	message_payload blob,
	PRIMARY KEY (queue_type, message_id))
	WITH ID = 72bd4080-6eee-11ec-a8a2-19be5e7c5c14
	AND CLUSTERING ORDER BY (message_id ASC)
	AND bloom_filter_fp_chance = 0.1
	AND dclocal_read_repair_chance = 0.1
	AND crc_check_chance = 1.0
	AND default_time_to_live = 0
	AND gc_grace_seconds = 864000
	AND min_index_interval = 128
	AND max_index_interval = 2048
	AND memtable_flush_period_in_ms = 0
	AND read_repair_chance = 0.0
	AND speculative_retry = '99PERCENTILE'
	AND comment = ''
	AND caching = { 'keys': 'ALL', 'rows_per_partition': 'NONE' }
	AND compaction = { 'class': 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy' }
	AND compression = { 'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor' }
	AND cdc = false
	AND extensions = {  };
