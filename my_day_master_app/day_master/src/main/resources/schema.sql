DROP TRIGGER IF EXISTS update_recurring_events_modtime ON recurring_events;
DROP TRIGGER IF EXISTS update_single_events_modtime ON single_events;

DROP FUNCTION IF EXISTS update_modified_column;

CREATE TABLE IF NOT EXISTS recurring_events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    title TEXT NOT NULL,
    rrule TEXT NOT NULL,
    timezone TEXT NOT NULL,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

COMMENT ON COLUMN recurring_events.rrule IS 'FREQ=DAILY;INTERVAL=1 или FREQ=WEEKLY;BYDAY=MO,WE,FR';

CREATE TABLE IF NOT EXISTS single_events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    title TEXT NOT NULL,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS recurring_events_exceptions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    recurring_event_id UUID NOT NULL REFERENCES recurring_events(id) ON DELETE CASCADE,
    exception_time TIMESTAMPTZ NOT NULL -- Точное время начала исключаемого события
);

CREATE INDEX IF NOT EXISTS idx_recurring_user ON recurring_events(user_id);
CREATE INDEX IF NOT EXISTS idx_recurring_active ON recurring_events(user_id) WHERE NOT is_deleted;

CREATE INDEX IF NOT EXISTS idx_single_user ON single_events(user_id);
CREATE INDEX IF NOT EXISTS idx_single_timerange ON single_events(start_time, end_time);

CREATE INDEX IF NOT EXISTS idx_exceptions_event_time ON recurring_events_exceptions(recurring_event_id, exception_time);
CREATE INDEX IF NOT EXISTS idx_exceptions_time ON recurring_events_exceptions(exception_time);